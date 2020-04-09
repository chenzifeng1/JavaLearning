# HashMap
JDK1.8 之前 HashMap 由 数组+链表 组成的，数组是 HashMap 的主体，链表则是主要为了
解决哈希冲突而存在的（“拉链法”解决冲突）.JDK1.8 以后在解决哈希冲突时有了较大的变化，
当链表长度大于阈值（默认为 8）时，将链表转化为红黑树（将链表转换成红黑树前会判断，
如果当前数组的长度小于 64，那么会选择先进行数组扩容，而不是转换为红黑树），以减少搜索时间。

## 底层数据结构


## 重要方法
#### 扰动函数-hash函数 
HashMap将key的hashCode经过扰动函数处理得到对应的hash值，然后通过```(n-1)&hash```判断当前元素的存放位置。
 如果当前位置存在元素，则判断已存在的元素与要存储的元素的hash值及key是否相同。如果相同则覆盖。不同则使用拉链法解决冲突。
   
通过扰动函数可以减少碰撞。
```java
    public class HashMap{

        static final int hash(Object key) {
            int h;
            return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        }
}
```
