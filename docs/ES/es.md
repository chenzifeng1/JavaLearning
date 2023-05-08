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
有限状态机：
1. 存在有限个状态
2. 同一时间只能存于一种状态
3. 不同状态之间可以相互转化
4. 状态是无序的

FSM 相对于前缀树，可以对词条后缀也进行复用，进一步压缩了数据存储空间。
![image](https://user-images.githubusercontent.com/17842768/236736242-5d0810c8-7d57-4474-a55a-d4f9d5583816.png)
FSM通过指定final-node来匹配词项的检索结束条件  
同时FST还有以下几个特性：
- 确定性:在任何给定状态下，对于任何一个输入，都只能遍历一个transition。即FST是有向的
- 非循环性:不可能重复遍历同一个状态。只能沿有向边匹配到final节点或者匹配不到词项
- Final的唯一性:当且仅当有限状态机在输入序列的末尾处于终止（final）状态时，才能『接受』输入序列  

FST最大的特点就是可以实现KEY-VALUE的映射，相当于HashMap<Key,Value>，但是查询速度上不如HashMap快  
但是FST极大压缩了存储空间，降低了内存消耗，且在Lucence中有很多应用，比如说：倒排索引存储，同义词存储，搜索关键字建议等。

### 压缩算法


#### 倒排表的压缩算法
1. FOR：frame of reference （适合稠密数组）
- 通过相邻倒排表数据做差来压缩数据长度
    
2. RBM： Roaring Bitmap （适合稀疏数组）
- 通过相邻倒排表数据除以 65536 获取 得数和余数 来压缩

#### 词项索引的压缩算法
1. FST: Finit state Transducers
