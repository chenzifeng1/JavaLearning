# Hive
- 存储元数据的数据仓库,数据仓库是面向主题的，不允许对数据进行修改，会形成一条时间拉链。数据仓库可以收纳各种类型的数据  
- hive：解释器，编译器，优化器等 ： 底层执行的数据操作实际上是mapReduce，所以需要将sql语句解释为底层执行的mapReduce
- hive运行时，元数据存储在关系型数据库里面，意思是hive启动时所依赖的元数据可以放在关系型数据库里面，不然每次起来都会生成一个元数据文件。

 ![image](https://github.com/chenzifeng1/JavaLearning/assets/17842768/e678724b-1b5a-4b2b-b8c2-0138de75a127)


