// 一个简单的协程实现， 原文: https://c9x.me/articles/gthreads/code0.html
// 这里是给代码加了些注释



#include <assert.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

enum {
    MaxGThreads = 4,
    StackSize = 0x400000,
};

struct gt {
    struct gtctx {
        uint64_t rsp;
        uint64_t r15;
        uint64_t r14;
        uint64_t r13;
        uint64_t r12;
        uint64_t rbx;
        uint64_t rbp;
    } ctx;
    enum {
        Unused,   // 没在用
        Running,  // 运行中
        Ready,    // 在用, 但 yield 了
    } st;
};

struct gt gttbl[MaxGThreads];
struct gt *gtcur;

void gtinit(void);
void gtret(int ret);
void gtswtch(struct gtctx *old, struct gtctx *new);
bool gtyield(void);
static void gtstop(void);
int gtgo(void (*f)(void));

void
gtinit(void)
{
    gtcur = &gttbl[0]; // 设定协程0 是"主协程" main
    gtcur->st = Running;
}

void __attribute__((noreturn))
gtret(int ret) // 当前协程 return
{
    if (gtcur != &gttbl[0]) { // 当前协程不是 main
        gtcur->st = Unused;
        gtyield();
        assert(!"reachable");
    }
    while (gtyield())
        ;
    exit(ret);
}

// 尝试yield当前协程， 切换到下一个 Ready 的协程. 如果没有Ready的， 则返回false.
bool
gtyield(void)
{
    struct gt *p;
    struct gtctx *old, *new;

    p = gtcur;
    while (p->st != Ready) { // 循环遍历协程列表, 找到一个Ready. 如果都非Ready, return fale
        if (++p == &gttbl[MaxGThreads])
            p = &gttbl[0];
        if (p == gtcur)
            return false;
    }

    if (gtcur->st != Unused) // 说明当前协程gtcur 是running, 则将其"挂起"(设为Ready)
        gtcur->st = Ready;
    p->st = Running;
    old = &gtcur->ctx;
    new = &p->ctx;
    gtcur = p;
    gtswtch(old, new);  // 切换协程, gtswitch是汇编代码.
    return true;
}

// 运行协程代码f() 后的返回地址.  详情见见gtgo().
static void
gtstop(void) { gtret(0); }

// 找到一个 Unsued 的协程
// 初始化该协程(申请用户态栈)
// 将 gstop(), f() 函数指针入栈
// 将协程状态置为 Ready (等待调度)
int
gtgo(void (*f)(void))
{
    char *stack;
    struct gt *p;

    // 找到一个 Unused 的协程， 找不到返回-1
    for (p = &gttbl[0];; p++)
        if (p == &gttbl[MaxGThreads])
            return -1;
        else if (p->st == Unused)
            break;

    // 为找到的协程，初始化context.
    
    // 在堆里申请一段内存作为这个协程的栈 (用户态栈)
    stack = malloc(StackSize);
    if (!stack)
        return -1;
        
    // 将 gtstop() 函数指针入栈.  目的是使得gtstop 是称为 函数 f() 的返回的地址.
    *(uint64_t *)&stack[StackSize -  8] = (uint64_t)gtstop;
    // 将 f() 函数指针入栈
    *(uint64_t *)&stack[StackSize - 16] = (uint64_t)f;
    // 寄存器 rsp 指向栈顶
    p->ctx.rsp = (uint64_t)&stack[StackSize - 16];
    p->st = Ready;

    return 0;
}


/* Now, let's run some simple threaded code. */

void
f(void)
{
    static int x;
    int i, id;

    id = ++x;
    for (i = 0; i < 10; i++) {
        printf("%d %d\n", id, i);
        gtyield();
    }
}

int
main(void)
{
    gtinit();
    gtgo(f);
    gtgo(f);
    gtret(1);
}





/* 关于寄存器

寄存器 rsp 指向栈顶
调用函数时.  当参数少于7个时， 参数从左到右放入寄存器: rdi, rsi, rdx, rcx, r8, r9

*/

.globl _gtswtch, gtswtch
_gtswtch:
gtswtch:

        mov     %rsp, 0x00(%rdi)
        mov     %r15, 0x08(%rdi)
        mov     %r14, 0x10(%rdi)
        mov     %r13, 0x18(%rdi)
        mov     %r12, 0x20(%rdi)
        mov     %rbx, 0x28(%rdi)
        mov     %rbp, 0x30(%rdi)

        mov     0x00(%rsi), %rsp
        mov     0x08(%rsi), %r15
        mov     0x10(%rsi), %r14
        mov     0x18(%rsi), %r13
        mov     0x20(%rsi), %r12
        mov     0x28(%rsi), %rbx
        mov     0x30(%rsi), %rbp

        ret

