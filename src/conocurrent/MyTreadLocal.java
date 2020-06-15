package conocurrent;

import java.lang.ref.WeakReference;

/**
 * @program: conocurrent
 * @author: chenzifeng
 * @description:
 * @create: 2020-06-11 15:49
 **/

public class MyTreadLocal {

    Thread thread = new Thread();

    ThreadLocal<String> stringThreadLocal = null;


    /**
     * ThreadLocalMap，相当于为ThreadLocal定制的一个Map
     */
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

}