package container.ArrayList;

import java.util.*;

/**
 *List接口在Collection的基础上增加了大量方法，使其可以在list中间增加和删除元素
 *  两种基本类型的list
 *      1.ArrayList: 长于随机访问元素
 *          ArrayList 继承自AbstractList类，实现了List<E>, RandomAccess, Cloneable, java.io.Serializable方法
 *          默认容量为10
 *      2.LinkedList:通过较低代价在List中间增加和删除元素，并且提供了优化的顺序访问
 *
 *      ArrayList实现中有一个被transient修饰的Object实例变量elementData来做存储元素的容器。
 */


public class MyArrayList<E> implements List<E>,RandomAccess{
    private ArrayList<Integer> arrayList;

    private int size;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
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
