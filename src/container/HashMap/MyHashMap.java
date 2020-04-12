package container.HashMap;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.Serializable;
import java.util.*;


public class MyHashMap<K, V> extends AbstractMap<K, V>
        implements Map<K, V>, Cloneable, Serializable {

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
    transient Node<K, V>[] table;
    //存放具体元素的集合
    transient Set<Map.Entry<K, V>> entrySet;

    transient int size;
    transient int modCount;
    //临界值：当容量*装填因子超过该值就会进行扩容
    int threshold;
    //hash表的装填因子
    float loadFactor;

    /*-------------------------------constructor------------------------------------*/

    public MyHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public MyHashMap(int initialCapacity){
        this(initialCapacity,DEFAULT_LOAD_FACTOR);
    }

    /**
     * 构造方法：判断初始化容量是否在合法范围，以及获取
     * @param initialCapacity 初始化容量
     * @param loadFactor 装填因子
     */

    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity :" + initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor < 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load Factor :" + loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    /**
     * 内部节点类Node,JDK1.8之后，会将在解决冲突时，将长度超过8的链表转化为红黑树
     *
     * @param <K>
     * @param <V>
     */
    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * final方法不能被子类重写
         *
         * @return
         */
        public final K getKey() {
            return key;
        }


        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }


        /**
         * 重写Node的hashCode: 其值为 key的hashCode与value的hashCode做异或运算
         * 异或位运算：1^1 = 0 ;  1^0 = 1
         *
         * @return
         */
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }


        /**
         * 判断对象节点与参数节点是否相同
         * 首先判断对象节点与参数节点的引用是否相同，如果引用相同则一定是同一个节点
         * 若引用不同：判断参数节点是否为Map.Entry的一个实例，因为对象节点已经实现了Map.Entry
         * 如果参数节点为Map.Entry的一个实例，则分别判断两节点的key,value是否对应相等。
         * 注：判断key,value时不能用 == 来判断，因为key,value可能为引用类型。
         * 所以，应该使用Objects的equals(Object,Object)方法判断
         *
         * @param o
         * @return
         */
        public final boolean equals(Object o) {
            if (this == o)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Entry<?, ?>) o;
                if (Objects.equals(e.getKey(), key) && Objects.equals(e.getValue(), value))
                    return true;
            }
            return false;
        }


    }
    /*--------------------protected way (保护类方法区）----------------------*/

    /**
     *
     * @param map
     * @param exivt 如果map已经被初始化了，则为false，否则为true
     */
    final void putMapEntries(Map<? extends K,? extends V> map,boolean exivt){


    }
    /*------------------- static utilities (静态方法区)----------------------*/

    /**
     * 扰动函数，减少元素存储时的碰撞
     *
     * @param key
     * @return 返回key的hash值，HashMap会根据该值为该元素定位存储的位置
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    /**
     *
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 10);
        return (n < 0) ? 1 : (n > MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /*--------------------------------TreeNode------------------------------------------------------------*/


    /**
     * 内部红黑树节点类
     * 红黑树：节点要么是黑色，要么是红色
     * 1.根节点和叶节点为黑色
     * 2.红节点的两个子节点为黑色
     * 3.从任一节点到叶节点的路径包含相同数量的黑色节点-如果某节点A的子节点为黑色，则节点A有两个黑色子节点。
     * 不然无法满足从该节点到可达的叶节点路径上黑色节点相同。
     *
     */
    /*
    static final class TreeNode extends LinkedHashMap.Entry<K,V>{
        TreeNode<K,V> parents; //父节点
        TreeNode<K,V> left;//左孩子
        TreeNode<K,V> right;//右孩子
        TreeNode<K,V> pre;//删除节点时应断开链接
        boolean red;//是否为红节点

        TreeNode(int hash,K key,V value,Node<K,V> next) {
            super(hash,key,value,next);
        }

        **
         * 得到红黑树的根节点，通过迭代不断向上遍历，直到找到parents为空的节点，该节点即为根节点
         * @return
         *
        final TreeNode root(){
            for(TreeNode<K,V> r = this,p;;){
               if((p = r.parents)==null)
                   return r;
               r = p;
            }
        }



    }
    */


}
