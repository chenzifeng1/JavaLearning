# ArrayList详解

详细介绍ArrayList源码，分别从实现接口、私有变量和方法以及内部类介绍。

## 实现接口及私有变量
在ArrayList中，继承自父类AbstractList<E>，并且实现了List，RandomAccess(随机访问标志接口)，Cloneable(可拷贝)以及序列化接口
```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    
    private static final int DEFAULT_CAPACITY = 10;  
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    transient Object[] elementData; // non-private to simplify nested class access
    private int size;

}
```
### 私有变量介绍
- DEFAULT_CAPACITY：默认初始化容量，若初始化时不指定ArrayList的容量，在默认为10。
- EMPTY_ELEMENTDATA：共享的一个空元素ArrayLIst，如果想要判断一个ArrayList是否为空的可以与该变量进行比较，此变量为静态变量，不必每次实例化出空的变量了。
- DEFAULTCAPACITY_EMPTY_ELEMENTDATA：共享的一个默认容量的ArrayList实例，如同EMPTY_ELEMENTDATA一样，所有默认的容量为默认值的ArrayList可以引用该变量。
- elementData：ArrayList的底层实现，就是一个Object数组，这个变量就是来存储ArrayList元素的缓冲区，ArrayList的容量就是这个缓冲区的长度。此变量被transient所修饰，即该变量不会被序列化存储到磁盘，只会在内存中驻留。当然有相应的方法将elementData写入内存。
- size：ArrayList实例中元素的个数。
- modCount:在ArrayList的所有涉及结构变化的方法中都增加modCount的值，包括：add()、remove()、addAll()、removeRange()及clear()方法。
### 核心方法介绍
[ArrayList源码分析](https://github.com/chenzifeng1/JavaLearning/blob/master/src/container/ArrayList/MyArrayList.java)

