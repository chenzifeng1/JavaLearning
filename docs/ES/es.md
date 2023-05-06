# es
<img width="919" alt="image" src="https://user-images.githubusercontent.com/17842768/200323184-b6d07fe8-ec90-4fe7-9446-2ff7abf6cafc.png">

搜索引擎应该具备的要求
- 查询速度快： 1.高效的压缩算法  2. 快速编码和解码能力
- 结果准确：BM25 / TF-IDF
- 检索结果丰富： 召回率

### 前缀树
共享相同前缀，但是后缀不共享，在存储时也没有办法做到极致复用。
![image](https://user-images.githubusercontent.com/17842768/236600656-30bb85d3-13fc-4dd5-8257-a140305943cf.png)


### FST （有限状态转换机）
FSM （finite state machines）


### 压缩算法


#### 倒排表的压缩算法
1. FOR：frame of reference （适合稠密数组）
- 通过相邻倒排表数据做差来压缩数据长度
    
2. RBM： Roaring Bitmap （适合稀疏数组）
- 通过相邻倒排表数据除以 65536 获取 得数和余数 来压缩

#### 词项索引的压缩算法
1. FST: Finit state Transducers
