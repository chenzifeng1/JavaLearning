package container.HashMap;

import java.io.Serializable;
import java.util.*;


public class MyHashMap<K, V> extends AbstractMap<K, V>
        implements Map<K, V>, Cloneable, Serializable {
    /**
     * 默认初试化容量为16
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;     //值为16

    static final int MAX_CAPACITY = 1 << 30;


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
         * @return
         */
        public final int hashCode(){
            return Objects.hashCode(key)^Objects.hashCode(value);
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
         * @param o
         * @return
         */
        public final boolean equals(Object o){
            if(this == o)
                return true;
            if (o instanceof Map.Entry){
                Map.Entry<?,?> e = (Entry<?, ?>) o;
                if(Objects.equals(e.getKey(),key)&&Objects.equals(e.getValue(),value))
                    return true;
            }
            return false;
        }


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
}
