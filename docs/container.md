## java持有对象-容器
> java容器分为两类，一个是Collection，是一个独立的序列，序列中每个元素类型都相同的;另一个是Map,可以视作由“键值对”组成数组。

- [Collection](#Collection)：
    - [Set](#Set)
    - [List](#List)
    - [SortedSet](#SortedSet)
    - [SortedMap](#SortedMap)
    - [HashSet](#HashSet)
    - [TreeSet](#TreeSet)
    - [ArrayList](#TreeSet)
    - [LinkedList](#LinkedList)
    - [Vector](#Vector)
    - [Collections](#Collections)
    - [Arrays](#Arrays)
    - [AbstractCollection](#AbstractCollection)

## Collection的方法
#### 排序
    void reverse(List list)//反转  
    void shuffle(List list)//随机排序  
    void sort(List list)//按自然排序的升序排序   
    void sort(List list, Comparator c)//定制排序，由Comparator控制排序逻辑  
    void swap(List list, int i , int j)//交换两个索引位置的元素  
    void rotate(List list, int distance)//旋转。当distance为正数时，将list后distance个元素整体移到前面。当distance为负数时，将 list的前distance个元素整体移到后面。    
#### 查找。替换  
    int binarySearch(List list, Object key)//对List进行二分查找，返回索引，注意List必须是有序的    
    int max(Collection coll)//根据元素的自然顺序，返回最大的元素。 类比int min(Collection coll)  
    int max(Collection coll, Comparator c)//根据定制排序，返回最大元素，排序规则由Comparatator类控制。类比int min(Collection coll, Comparator c)  
    void fill(List list, Object obj)//用指定的元素代替指定list中的所有元素。  
    int frequency(Collection c, Object o)//统计元素出现次数  
    int indexOfSubList(List list, List target)//统计target在list中第一次出现的索引，找不到则返回-1，类比int lastIndexOfSubList(List source, list target).  
    boolean replaceAll(List list, Object oldVal, Object newVal), 用新元素替换旧元素  
    


## 面试考察
- List,Set和Map的区别
    List接口存储一组不唯一的有序对象。    
    Set存储对象不允许有重复，即不会多个元素引用相同对象。  
    Map使用键值对存储，Key-Value.Key值唯一，多个key可以对应相同的value。
- ArrayList与LinkedList的区别  
    线程安全：两者都是不同步的，无法保证线程安全  
    底层数据结构：  
    ArrayList的底层实现是Object数组
```java
public class ArrayList<E>{
     transient Object[] elementData;
     
        public ArrayList(int initialCapacity) {
            if (initialCapacity > 0) {
                this.elementData = new Object[initialCapacity];
            } else if (initialCapacity == 0) {
                this.elementData = EMPTY_ELEMENTDATA;
            } else {
                throw new IllegalArgumentException("Illegal Capacity: "+
                                                   initialCapacity);
            }
        }
}
```
   LinkedList的底层实现是双向链表,通过一个内部类Node来实现双向链表。Node类存在两个引用first和last来充当前序后继的指针（java中没有指针的概念，此处借此来增加理解）。
```java
public class LinkedList<E>{
    transient int size = 0;
    transient Node<E> first;    
    transient Node<E> last;
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
    /**
     *构造方法
    */
    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
        modCount++;
    }
}
```
- 关于插入和查询  
    两者在插入和查询方面的区别就是数组与链表在插入和查询中的表现了。ArrayList代表数组，可以实现随机查询，查询时间复杂度为O(1)。ArrayList默认是尾插，即在执行add(E e)方法时新
    进入的元素在数组尾部，此操作的时间复杂度为O(1)，但是如果执行add(int index, E element)时，使用了数组拷贝来留出要插入的元素的位置。时间复杂度为O(N-i)。
    而LinkedList因为使用链表的数据结构，所以在插入时只需要改变前后的引用指向即可，插入的时间复杂度为O(1)。但是由于遍历必须为顺序遍历，想得到某个元素必须从头部或尾部进行，所以时间复杂度为O(n)。
- 关于内存空间消耗  
    ArrayList的空间浪费主要是因为会在数组后面预留一定的容量空间。而LinkedList的每一个元素都会有前序后继的引用占空间。
    
## 相关内容
- 关于RandomAccess()接口，该接口的作用更大是一个标识，标志着实现该接口的类可以随机访问元素。
- ArrayList中有一个方法ensureCapacity(int minCapacity),这个是提供给用户使用的，在新增大量元素之前使用这个方法可以减少增量重新分配的次数。
```java
public class ArrayList{
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length
            && !(elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
                 && minCapacity <= DEFAULT_CAPACITY)) {
            modCount++;
            grow(minCapacity);
        }
    }
}
```
- jdk1.8之后hashmap解决冲突的方法发生了改变，在达到链表阈值之后转为红黑树来减少搜索时间