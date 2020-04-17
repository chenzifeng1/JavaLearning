# HashMap
JDK1.8 之前 HashMap 由 数组+链表 组成的，数组是 HashMap 的主体，链表则是主要为了
解决哈希冲突而存在的（“拉链法”解决冲突）.JDK1.8 以后在解决哈希冲突时有了较大的变化，
当链表长度大于阈值（默认为 8）时，将链表转化为红黑树（将链表转换成红黑树前会判断，
如果当前数组的长度小于 64，那么会选择先进行数组扩容，而不是转换为红黑树），以减少搜索时间。

## 重要属性

```java
import java.io.Serializable;import java.util.AbstractMap;public class HashMap<K,V> extends AbstractMap
            implements Map<K,V>, Cloneable,Serializable{
    //序列号
    private static final long serialVersionUID = 362498820763181265L;
    //默认初试容量
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    //最大容量
    static final int MAXIMUM_CAPACITY = 1 << 30;
    //默认填充因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    //当桶(bucket)节点数大于该值就会转换成红黑树
    static final int TREEIFY_THRESHOLD = 8;
    //当桶(bucket)节点数小于该值就会转换成链表
    static final int UNTREEIFY_THRESHOLD = 6;
    //桶中结构转换成红黑树对应的table的最小容量
    static final int MIN_TREEIFY_CAPACITY = 64;
    //存放具体node的数组，第一次使用时初始化，用来存放数据，总是2的幂次倍
    transient Node<K,V>[] table; 
    //存放具体元素的集合
    transient Set<Map.Entry<K,V>> entrySet;
    
    transient int size;
    transient int modCount;
    //临界值：当容量*装填因子超过该值就会进行扩容
    int threshold;
    //hash表的装填因子
    float loadFactor;
}
```

- loadFactor   
加载因子是控制数组存放数据的疏密程度，loadFactor越趋近于1， 
那么 数组中存放的数据(entry)也就越多，也就越密，也就是会让链表的长度增加，
loadFactor越小，也就是趋近于0，数组中存放的数据(entry)也就越少，也就越稀疏。

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


## 内部类
内部类有两个，一个是节点类Node，作为元素的基本存储节点；另一个是树节点TreeNode,链表转红黑树时使用的节点。
具体代码解析见[HashMap源码分析](https://github.com/chenzifeng1/JavaLearning/blob/master/src/container/HashMap/MyHashMap.java)