package container.ArrayList;

import jdk.internal.util.ArraysSupport;

import java.lang.reflect.Array;
import java.util.*;

/**
 * List接口在Collection的基础上增加了大量方法，使其可以在list中间增加和删除元素
 * 两种基本类型的list
 * 1.ArrayList: 长于随机访问元素
 * ArrayList 继承自AbstractList类，实现了List<E>, RandomAccess, Cloneable, java.md.io.Serializable方法
 * 默认容量为10
 * 2.LinkedList:通过较低代价在List中间增加和删除元素，并且提供了优化的顺序访问
 * <p>
 * ArrayList实现中有一个被transient修饰的Object实例变量elementData来做存储元素的容器。
 */


public class ArrayListAnalysis<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable {
    /**
     * 默认初始化容量，若初始化时不指定ArrayList的容量，在默认为10。
     */

    Array array;
    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;    //可以分配的最大数组大小

    transient Object[] elementData;

    private int size;


    /**
     * 该字段记录列表对象的容量改动次数，迭代器的实现中使用了该字段
     */
    protected transient int modCount = 0;

    public ArrayListAnalysis() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 根据传入的参数来进行初始化
     * 参数大于0：创建一个Object数组
     * 参数等于0：将引用指向EMPTY_ELEMENTDATA
     * 参数小于0：抛出参数不合法异常
     *
     * @param initCapacity
     */
    public ArrayListAnalysis(int initCapacity) {
        if (initCapacity > 0) {
            elementData = new Object[initCapacity];
        } else if (initCapacity == 0) {
            elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("MyArrayList" + initCapacity);
        }
    }

    /**
     * 传入参数为一个Collection的子类
     * Collection类的一个方法可以将Collection对象转为Array
     * 剩下所作的就是判断所得的对象是不是一个Object数组
     *
     * @param c
     */
    public ArrayListAnalysis(Collection<? extends E> c) {
        this.elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            if (elementData.getClass() != Object[].class) {
                elementData = Arrays.copyOf(elementData, size, Object[].class);
            }
        } else {
            elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * 将容量大小缩小至当前列表元素的个数
     * 因为要改动列表容量所以首先记录改动：modCount++
     * 之后判断size（元素个数）与elementData.length（容量）的大小，
     */
    public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    /**
     * 必要时进行扩容，使列表可以容纳元素所需的最小数量
     *
     * @param minCapacity 所需要的最小容量
     */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length
                && !(elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
                && minCapacity <= DEFAULT_CAPACITY)) {
            modCount++;
            grow(minCapacity);
        }
    }

    //得到最小扩容量
    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            // 获取默认的容量和传入参数的较大值
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    //判断是否需要扩容
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            //调用grow方法进行扩容，调用此方法代表已经开始扩容了
            grow(minCapacity);
    }

    /**
     * 通过扩容来确保能够存储最小容量参数的容量，该方法为私有方法，更多的使用在其他方法内部
     * 若无参数则默认容量+1
     *
     * @param minCapacity
     * @return
     */
    private Object[] grow(int minCapacity) {

        int oldCapacity = elementData.length;   //记录原有数组大小
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {  //若原数组不为空
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);     //获取新的数组容量
            return elementData = Arrays.copyOf(elementData, newCapacity);   //原数组拷贝扩容
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }
    /**
     * 返回当前列表内的元素个数 此处应该有一个多线程数据安全判断的方法，通过判断被修改的次数来判断该列表对象是否被多个线程所操作并进行了修改。
     * 该方法需要一个静态的内部类 subList来实现，之后在分析
     *         private void checkForComodification() {
     *             if (root.modCount != modCount)
     *                 throw new ConcurrentModificationException();
     *         }
     * @return
     */


    /**
     * 克隆方法，获取ArrayList对象的拷贝，ArrayList实现了接口Cloneable
     *
     * @return
     */
    public Object clone() {
        try {
            ArrayListAnalysis<?> v = (ArrayListAnalysis<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e); //如果发生异常是不应该的，因为该类是可克隆的
        }

    }

    @Override
    public int size() {
        //checkForComodification()
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) > 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * 以正确的顺序返回一个
     *
     * @return
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * 以正确顺序返回一个包含此列表所有元素的数组
     * 返回数组的运行时类型与指定参数相同，若指定参数a中可以容纳此列表元素，则拷贝到a中并返回
     * 若参数a大小无法容纳此列表元素，则返回一个与指定参数运行时类型相同的拷贝
     *
     * @param a   指定数组
     * @param <T> a的类型，要求返回的数组与此类型相同
     * @return
     */

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        }
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    /**
     * 按照索引返回指定类型的列表元素
     *
     * @param index
     * @return
     */
    E elementData(int index) {
        return (E) elementData[index];
    }

    static <E> E elementAt(Object[] es, int index) {
        return (E) es[index];
    }

    /**
     * 重写父类的add方法，该方法默认将元素插入列表尾部
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(E e) {
        modCount++;
        ensureExplicitCapacity(size);
        elementData[size++] = e;
        return true;
    }

    /**
     * 将元素插入指定下标出的位置
     *
     * @param e
     * @param elementData
     * @param s
     */
    public void add(E e, Object[] elementData, int s) {
        if (s > elementData.length) {
            elementData = grow();
        }
        elementData[s] = e;
        size += 1;
    }

    @Override
    public boolean remove(Object o) {
        final Object[] obs = this.elementData;
        final int s = this.size;
        int i = 0;
        found:
        {
            if (o == null) {
                for (i = 0; i < s; i++) {
                    if (obs[i] == null)
                        break found;
                }
            } else {
                for (i = 0; i < s; i++) {
                    if (o.equals(obs[i])) {
                        break found;
                    }

                }

            }
            return false;
        }
        fastRemove(this.elementData, i);
        return true;
    }

    /**
     * 通过移动数组元素来覆盖要移除的元素
     *
     * @param es
     * @param i
     */
    private void fastRemove(Object[] es, int i) {
        modCount++;
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
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
        modCount++;
        final Object[] o = this.elementData; //用final修饰引用并非无法改变引用包含的元素的内容，而是无法改变引用的指向
        for (int i = 0; i < size; i++) {
            o[i] = null;
        }
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
        modCount++;
        fastRemove(this.elementData, index);
        return null;
    }


    /**
     * 寻找元素索引：调用一个局部寻找元素索引的方法
     *
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {
        return indexOfRange(o, 0, size);
    }

    /**
     * 局部寻找元素索引，遍历数组，先判断所寻找的元素是否为空，然后根据条件来遍历数组
     *
     * @param o     目标数组
     * @param start 起始索引
     * @param end   终止索引
     * @return 若元素存在则返回元素下标，若不存在则返回-1
     */
    int indexOfRange(Object o, int start, int end) {
        Object[] es = elementData;
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 返回与参数相同的元素的下标，如果有多个相同则返回下标最大的元素的索引
     *
     * @param o
     * @return
     */
    @Override
    public int lastIndexOf(Object o) {
        return lastIndexOfRange(o, 0, size);
    }

    /**
     * 从后向前遍历，取第一个相同的元素下标
     *
     * @param o
     * @param start
     * @param end
     * @return
     */
    int lastIndexOfRange(Object o, int start, int end) {
        Object[] obs = elementData;
        if (o == null) {
            for (int i = size - 1; i >= start; i++) {
                if (o == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= start; i++) {
                if (o.equals(obs[i])) {
                    return i;
                }
            }
        }
        return -1;
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
