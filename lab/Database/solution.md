* 在 MySQL 构造以下场景，并描述SQL过程和原因（在 lab/Database/solution.md 里）
<pre>
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
</pre>

* 在 READ-UNCOMMITTED 隔离级别下，构造脏读
  * 过程

| Transaction 1 | Transaction 2 |
| ------ | ------ |
| BEGIN | BEGIN |
|INSERT INTO user VALUES (1, 1); | |
| | SELECT * FROM user WHERE id = 1; |
| COMMIT; | COMMIT; |

    现象：事务2 SELECT 会读到事务1 里未提交的 id=1 的 user

  * 原因
    “未提交读”隔离级别下有脏读问题

* 在 READ-COMMITTED 隔离级别下，构造不可重复读
* 在 REPEATBLE-READ 隔离级别下，构造幻读
* 在 SERIALIZABLE 隔离级别下，解决幻读
* 举出3个死锁的例子
