# LinkedList详解
LinkedList实现了List和Deque接口，并且实现了List接口中的所有操作，同时实现了Deque接口，使得LinkedList有了队列的特性。
但是LinkedList并不是线程安全的，如果要获得线程安全的List可以使用静态类Collections中的synchronizedList方法。  
```List list = Collections.synchnorizedList(new LinkedList(...))```

## 私有变量

- transient int size ：LinkedList对象内包含元素的个数，初始化为0。
- transient Node<E> first ：第一个节点的引用。
- transient Node<E> last : 最后一个节点的引用。
- 

## 构造方法
```java
public class LinkedList{
    public LinkedList(){}
    public LinkedList(Collection<? extends E> c){
        this();
        addAll(c);
    }
    public LinkedList(E e){
        
    }
}
```


