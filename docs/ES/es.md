# es
<img width="919" alt="image" src="https://user-images.githubusercontent.com/17842768/200323184-b6d07fe8-ec90-4fe7-9446-2ff7abf6cafc.png">

搜索引擎应该具备的要求
- 查询速度快： 1.高效的压缩算法  2. 快速编码和解码能力
- 结果准确：BM25 / TF-IDF
- 检索结果丰富： 召回率

### 压缩算法


#### 倒排表的压缩算法
1. FOR：frame of reference （适合稠密数组）
    
2. RBM： Roaring Bitmap （适合稀疏数组）

#### 词项索引的压缩算法
1. FST: Finit state Transducers
