# Mysql

## 索引
 索引应用场景：数据库存储大量数据，为了快速检索数据。


### 外键
在一个数据库内，只允许有一个唯一外键名。不允许外键名重复

## 查询
### [普通查询](https://www.yiibai.com/mysql/select-statement-query-data.html)
查询语句
``` mysql
SELECT 
    column_1, column_2, ...
FROM
    table_1
[INNER | LEFT |RIGHT] JOIN table_2 ON conditions
WHERE
    conditions
GROUP BY column_1
HAVING group_conditions
ORDER BY column_1
LIMIT offset, length;
```
SELECT语句由以下列表中所述的几个子句组成：
- **SELECT** 跟着是逗号分隔列或星号(\*)的列表，表示要返回所有列。
- **FROM** 指定要查询数据的表或视图。
- **JOIN** 根据某些连接条件从其他表中获取数据。
- **WHERE** 通过表达式过滤结果集中的行 [=,like,>,<等表达式]
- **GROUP** BY将一组行组合成小分组，并对每个小分组应用聚合函数。
- **HAVING** 过滤器基于GROUP BY子句定义的小分组。
- **ORDER BY** 指定用于排序的列的列表。
- **LIMIT** 限制返回行的数量。

### 多表查询
1. 内连接 
2. 外连接
3. 子查询



## 事务
在 MySQL 中只有使用了 Innodb 数据库引擎的数据库或表才支持事务。用来管理 insert,update,delete 语句，因为只有对数据库做出更新时，才需要操作满足事务进行。
事务是必须满足4个条件（ACID）：：原子性（Atomicity，或称不可分割性）、一致性（Consistency）、隔离性（Isolation，又称独立性）、持久性（Durability）。
1. 原子性：一个事务（transaction）中的所有操作，要么全部完成，要么全部不完成，不会结束在中间某个环节。事务在执行过程中发生错误，会被回滚（Rollback）到事务开始前的状态，就像这个事务从来没有执行过一样。
2. 一致性：在事务开始之前和事务结束以后，数据库的完整性没有被破坏。这表示写入的资料必须完全符合所有的预设规则，这包含资料的精确度、串联性以及后续数据库可以自发性地完成预定的工作。
3. 隔离性：数据库允许多个并发事务同时对其数据进行读写和修改的能力，隔离性可以防止多个事务并发执行时由于交叉执行而导致数据的不一致。事务隔离分为不同级别，包括读未提交（Read uncommitted）、读提交（read committed）、可重复读（repeatable read）和串行化（Serializable）。
4. 持久性：事务处理结束后，对数据的修改就是永久的，即便系统故障也不会丢失。

事务控制语句：

BEGIN/START TRANSACTION 显示开启事务。
COMMIT 提交事务，使对数据库做的操作持久化下来
ROLLBACK 回滚事务，回滚会结束用户的事务，并撤销用户所有未提交的修改
SAVEPOINT identifier 在事务中创建保存点，一个事务可以有多个保存点
ROLLBACK TO identifier 回滚至保存点，保存点的作用是，回滚时不必撤销全部操作，只需要撤销保存点以后的操作。

我们在向多个表修改数据时，可以通过事务来做到修改数据的一体性。
