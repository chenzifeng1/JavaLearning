# Mysql

## 索引
 索引应用场景：数据库存储大量数据，为了快速检索数据。
 
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
