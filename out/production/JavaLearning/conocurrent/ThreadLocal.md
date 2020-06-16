## ThreadLocal

ThreadLocal导致内存泄漏的原因：(ThreadLocal底层存储实现)
```java
    static class ThreadLocalMap {
        static class Entry extends WeakReference<ThreadLocal<?>> {
            /**
             * The value associated with this ThreadLocal.
             */
            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }

        }
    }
```
Entry 继承了弱引用的抽象类，每个Entry中包含一个Object value。value不是弱引用，这一设定也就是ThreadLocal会OOM的原因了
在jvm的垃圾回收机制中，强引用不会被回收，软引用一般不会被回收、内存不够时会被回收，弱引用在进行GC时会被回收，但是不进行GC就会留在内存，虚引用会立刻被回收。
LocalTread中来存储数据点Entry继承的是弱引用，ThreadLocalMap中key是弱引用，而value不是。所以在GC的时候可能会出现key被回收，而value存储在内存中。这样GC无法找到对应的value，无法清理，
于是value堆积过多就会造成内存泄露。