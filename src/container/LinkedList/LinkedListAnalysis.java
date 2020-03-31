package container.LinkedList;


import java.util.*;

/**
 * 对LinkedList源码分析
 */
public class LinkedListAnalysis<E> extends AbstractSequentialList<E>
        implements List<E>, Deque<E>, Cloneable, java.io.Serializable {

    private LinkedList<Integer> linkedList = new LinkedList<Integer>();
//  获得线程安全的LinkedList
//  List<String> list = Collections.synchronizedList(new LinkedList<String>();

    transient int size = 0;
    //  链表的第一个节点的引用
    transient Node<E> first;
    //  链表的最后一个节点的引用
    transient Node<E> last;


    public LinkedListAnalysis() {
    }


    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * LinkedList的底层实现的数据结构是双向链表，所以使用内部类Node作为双向链表的结点。
     *
     * @param <E>
     */
    private class Node<E> {
        E item; //节点值

        Node<E> next; //后继节点引用
        Node<E> prev; //前驱节点引用

        public Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

//私有方法

    /**
     * 判断索引是否合法，索引必须在0-size之间
     *
     * @param index
     * @return
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 返回溢出信息 index ： size
     * @param index
     * @return
     */
    private String outOfMsg(int index){
        return "index:"+index+", size:"+size;
    }

    /**
     * 检测索引是否合法，在合法范围内就返回true，否则返回false
     * @param index
     * @return
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }


    private void checkElementIndex(int index){
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfMsg(index));
    }


//protected方法

    /**
     * 将e作为最后一个节点链入列表,LinkedList实现的是Deque接口，所以添加新元素的方式是按照队列的模式，先进先出。
     * 注：first与last两个节点不是空的头节点，而是包含元素的节点
     *
     * @param e
     */
    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        // 如果l为空，说明队列内部无元素，那么将first也指向新建立的节点
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * 让e作第一个元素加入队列
     *
     * @param e
     */
    void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
        modCount++;

    }


    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public void addFirst(E e) {
        linkFirst(e);
    }

    @Override
    public void addLast(E e) {
        linkLast(e);
    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
}
