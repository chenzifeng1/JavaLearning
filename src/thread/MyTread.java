package thread;

import java.util.concurrent.locks.ReentrantLock;

public class MyTread implements Runnable{
    private Thread thread;


    /**
     * ReentrantLock 可重入锁
     */
    private ReentrantLock reentrantLock;
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
