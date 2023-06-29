# es
<img width="919" alt="image" src="https://user-images.githubusercontent.com/17842768/200323184-b6d07fe8-ec90-4fe7-9446-2ff7abf6cafc.png"> 

搜索引擎应该具备的要求  
- 查询速度快： 1.高效的压缩算法  2. 快速编码和解码能力  
- 结果准确：BM25 / TF-IDF  
- 检索结果丰富： 召回率    

Mysql的B+树索引为什么不能解决大数据检索的问题  
1.  索引字段往往很长，用B+树构造之后树的深度可能会很长，IO增多
2.  索引可能会失效
3.  精准度差
解释：
全文搜索场景下，文本内容是索引的关键，如果用B+树来构造索引时，每个节点的数据量会比较大，而且全文索引基本都是模糊查询，很可能造成索引失效。  

解法： lucene  
--  基于java开发的全文检索库，具有高性能，可伸缩的特性。且是开源免费的

![image](https://github.com/chenzifeng1/JavaLearning/assets/17842768/287b1f37-c106-4a85-a9b3-cb0f7003cf87)


## 倒排索引
![image](https://github.com/chenzifeng1/JavaLearning/assets/17842768/2b22d01b-8e5e-4523-b086-6480885243ac)
数据结构
1. 倒排表
2. 词项字典
3. 词项索引

### 压缩算法

#### 倒排表的压缩算法
1. FOR：frame of reference （适合稠密数组）
![image](https://github.com/chenzifeng1/JavaLearning/assets/17842768/82b90fe0-03d1-4330-afb3-279b1c4720f6)
- 通过相邻倒排表数据做差来压缩数据长度  
- 倒排表中，某个词项对应的文档id列表比较稠密时，比如，100W个文档id，存储的是int类型，所占用的存储空间是400Wbyte。
- FOR压缩算法，将id集合分组，每组中id的范围近似，可以以分组中最大的id值为存储边界。比如说某个分组中有10个元素，最大的id为220，可以用8bit即1byte的空间来存储
- 该分组如果没有压缩，存储的空间为40byte，压缩之后，存储的空间为10byte+1Byte，其中10byte为数据空间，1byte为标记空间（标记分组元素占用空间大小）。
    
2. RBM： Roaring Bitmap （适合稀疏数组）
<img width="775" alt="image" src="https://github.com/chenzifeng1/JavaLearning/assets/17842768/8cb59e4d-bd13-4bc4-bf64-8387bb2da65d">

- 对于倒排表中，文档id为稀疏数组的情况，再用差值计算难以节约空间。  
- 将文档id除以65535，得到除数和余数。除数和余数的取值范围都小于等于2的16次方  
- 且对于某个词项来说，关联的文档id不会存在重复。在这种情况下，除数可以用short类型的数组来存放，余数则采用container来存放  
- container分为两种类型；1. ArrayContainer 2. BitmapContainer   
- ArrayContiner每个余数用short类型存放，一个short占用2字节。如图所示，6个文档id如果直接存储占用了6*4=24字节，采用ArrayContiner共花费2*3+2*6= 18字节  
- BitmapContainer： 当文档id很多时，直接存储余数也比较消耗空间。于是用bitmap的方式代表对应余数是否存在，每个container里面元素的取值范围为0-65535，且不会重复。那么我们采用一个65536位的bit可以覆盖所有情况  
- 通过相邻倒排表数据除以 65536 获取 得数和余数 来压缩


### 前缀树
共享相同前缀，但是后缀不共享，在存储时也没有办法做到极致复用。
![image](https://user-images.githubusercontent.com/17842768/236600656-30bb85d3-13fc-4dd5-8257-a140305943cf.png)  
词项都是按照字典序排序的，因此可以确定词项中哪些到了终止节点


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
同时FSM还有以下几个特性：
- 确定性:在任何给定状态下，对于任何一个输入，都只能遍历一个transition。即FST是有向的
- 非循环性:不可能重复遍历同一个状态。只能沿有向边匹配到final节点或者匹配不到词项
- Final的唯一性:当且仅当有限状态机在输入序列的末尾处于终止（final）状态时，才能『接受』输入序列  

FST最大的特点就是可以实现KEY-VALUE的映射，相当于HashMap<Key,Value>，但是查询速度上不如HashMap快  
但是FST极大压缩了存储空间，降低了内存消耗，且在Lucence中有很多应用，比如说：倒排索引存储，同义词存储，搜索关键字建议等。

#### FST在luncene的实现
![image](https://github.com/chenzifeng1/JavaLearning/assets/17842768/3cb26699-e991-4fdc-949a-2ae6d438179c)  
当输入abda词项时  
![image](https://github.com/chenzifeng1/JavaLearning/assets/17842768/43679474-73f5-464a-9b4d-6bd25360c655)  
输入abdb词项时  
![image](https://github.com/chenzifeng1/JavaLearning/assets/17842768/38ecc9a6-a603-4b81-a352-01b93d463d8d)  

Node:
用来描述节点状态，有两种类型的node：  
1. UnCompileNode：未 处理的节点
2. CommileNode： 处理过的节点，从内存中取出转化为二进制的字节数组落在磁盘中
 
forntier[]: 
用来存放UnCompileNode，待处理的节点Arc

ByteStore bytes current[]:
用来存储CompileNode的二进制数组

PendingTerm：每个字符都是一个PendingTerm
PendingBlock：将多个PeningTerm组合在一起的块


#### 词项索引的压缩算法
1. FST: Finit state Transducers


## tim和tip文件的内部结构
![image](https://github.com/chenzifeng1/JavaLearning/assets/17842768/0a46b275-3798-4812-8a41-d838d2aef744)


# ES基础知识
## 路由计算
当客户端向ES的协调节点发送一条数据写入请求时，协调节点通过以下公式来计算数据应该存储在哪个分片中：  
 - shard = hash(routing)%number_of_primary_shards

存储分片的id = routing（用户可以自定义，默认取文档id）的hash值 对 索引对应的分片数量取余数。 
比如说，文档id的hash值是100，一个索引对应的分片数量是5，那么该数据在写入时就会被分配到分片20
这也是为何索引对应的分片数量无法改变的原因，一旦改变，计算数据写入分片时就会与原来的不一致，在读取数据的时候可能找不到原有的存储分片

## 创建索引
场景：做酒店搜索，搜索字段包括酒店标题，所属城市和房价等信息。  
对于酒店标题，要求可以进行模糊所有，因此定义类型为文本（text）。所属城市进行精准匹配即可，类型为关键词（keyword）。价格需要进行大小比较，类型设置为浮点数（double）。  
假设使用默认分片数和副本数，创建索引语句如下：  
```
curl -H "Contenet-Type:application/json" -XPUT http://127.0.0.1:9200/hotel -d
{
    "mapping":{
        "properties":{
            "title":{
                "type":"text"
            },
            "city":{
                "type":"keyword"
            },
            "price":{
                "type":"double"
            }
        }
    }
}
```



