public class DynamicDispatch {

    static abstract class Human { 
        protected abstract void sayHello(Human human);
        protected abstract void sayHello(Man man);
        protected abstract void sayHello(Woman woman);
    }

    static class Man extends Human {
        @Override
        protected void sayHello(Human human) {
            System.out.println("man say hello to human");
        } 
        @Override
        protected void sayHello(Man man) {
            System.out.println("man say hello to man");
        } 
        @Override
        protected void sayHello(Woman woman) {
            System.out.println("man say hello to woman");
        } 
    }

    static class Woman extends Human {
        @Override
        protected void sayHello(Human human) {
            System.out.println("woman say hello to human");
        } 
        @Override
        protected void sayHello(Man man) {
            System.out.println("woman say hello to man");
        } 
        @Override
        protected void sayHello(Woman woman) {
            System.out.println("woman say hello to woman");
        } 
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello(man);
        woman.sayHello(man);
    }
}
