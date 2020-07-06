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

## Map的几个实现
![map的常见实现](../../../picture/map的几个实现.PNG)

Map作为一个接口，有几个实现类需要注意一下：
HashMap：最常见的Map的实现，以键值对为单位存储，存储过程:首先获取键值对中的key的hashcode，使用扰动函数hash来得到对应的hash，
根据一定的算法来得到该元素在table上的存放位置，如果该位置没有元素，则将元素加到table的该位置上。如果table上的该位置有元素，
则判断两个元素的hash和key是否一样，如果一样则覆盖，不一样则链到已有元素的后面。所以HashMap要求不能有一样的key，
但是针对不同的key可以有相同的value。另外如果随着元素在某个位置上的链表长度越来越长，在jdk1.8之后，某个链表长度超过8，
则会将链表转换为红黑树。注意的是，最好一开始根据自己的需求设置尽量合理的table长度，因为一旦改变table长度，就要面临rehash（重哈希），所有元素会重新分配，时间成本较高。
HashMap是线程不安全的，在多线程的环境下可能造成数据不一致的情况。可以使用ConcurrentHashMap这个线程安全的类。  

HashTable:现在使用并不是特别多。HashMap允许最多一个key为null，允许多个value是null。HashTable不允许任何一个key或value为null。
另外HashTable每次只能允许一个线程对其访问，所以是线程安全的Map。  

LinkedHashMap: HashMap的一个子类，保存了插入顺序，使用Iterator(迭代器)去遍历LinkedHashMap时，先遍历的是先插入的元素。
一般情况下，LinkedHashMap的遍历速度比HashMap要慢很多：HashTable是按顺序逐个遍历，遍历速度取决于元素的个数。

TreeMap：实现了SortMap能够把它保存到元素按键排序，默认按照升序的方式存储，可以自定义比较器。读取元素时，得到的元素是排好序的。

### 8个元素转树：
    符合泊松分布？
### 6个元素转链表
    缓冲，不至于加一个就转树，减一个就转列表    