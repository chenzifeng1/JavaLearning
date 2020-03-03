package container.ArrayList;

import jdk.internal.util.ArraysSupport;

import java.util.*;

/**
 * List接口在Collection的基础上增加了大量方法，使其可以在list中间增加和删除元素
 * 两种基本类型的list
 * 1.ArrayList: 长于随机访问元素
 * ArrayList 继承自AbstractList类，实现了List<E>, RandomAccess, Cloneable, java.io.Serializable方法
 * 默认容量为10
 * 2.LinkedList:通过较低代价在List中间增加和删除元素，并且提供了优化的顺序访问
 * <p>
 * ArrayList实现中有一个被transient修饰的Object实例变量elementData来做存储元素的容器。
 */


public class MyArrayList<E> implements List<E>, RandomAccess {
    /**
     * 默认初始化容量，若初始化时不指定ArrayList的容量，在默认为10。
     */
    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    transient Object[] elementData;

    private int size;

    protected transient int modCount = 0;

    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 根据传入的参数来进行初始化
     * 参数大于0：创建一个Object数组
     * 参数等于0：将引用指向EMPTY_ELEMENTDATA
     * 参数小于0：抛出参数不合法异常
     * @param initCapacity
     */
    public MyArrayList(int initCapacity) {
        if (initCapacity > 0) {
            elementData = new Object[initCapacity];
        } else if (initCapacity == 0){
            elementData = EMPTY_ELEMENTDATA;
        }else {
            throw new IllegalArgumentException("MyArrayList"+initCapacity);
        }
    }

    public MyArrayList(Collection<? extends E> c) {
        this.elementData = c.toArray();
        if ((size= elementData.length)!=0){
            if(elementData.getClass()!=Object[].class){
                elementData = Arrays.copyOf(elementData,size,Object[].class);
            }
        }else {
            elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * 扩容方法
     * @return
     */

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length
                && !(elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
                && minCapacity <= DEFAULT_CAPACITY)) {
            modCount++;
            grow(minCapacity);
        }
    }

    /**
     * 通过扩容来确保能够存储最小容量参数的容量，该方法为私有方法，更多的使用在其他方法内部
     * @param minCapacity
     * @return
     */
    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return elementData == EMPTY_ELEMENTDATA;
    }

    @Override
    public boolean contains(Object o) {
        if(o == null){

        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }


}
