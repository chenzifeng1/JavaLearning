package thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest implements Runnable {

    static Integer resource = 0; //共享资源

    public static void main( String args[]){

        ThreadTest obj = new ThreadTest();
//
//        for(int i =0 ;i<10;i++){
//            Thread thread = new Thread(obj,"thread"+i);
//            thread.start();
//        }


        synchronized (ThreadTest.class){
            resource++;
        }


    }


    @Override
    public void run() {
        System.out.println("thread:"+Thread.currentThread().getName() );
        ReentrantLock reentrantLock = new ReentrantLock();//
        reentrantLock.lock();//对以下代码进行加锁
        try{
             resource++; //对共享资源进行操作
            System.out.println(resource);
            Thread.sleep(100);

        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }finally {
            reentrantLock.unlock();//解锁
        }

    }
}
