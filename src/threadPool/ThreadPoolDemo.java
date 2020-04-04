package threadPool;

import thread.ThreadLocalDemo;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo<T> {
    private static int corePoolNum; //核心线程数
    private static int maxPoolNum;  //最大线程数

    //充当消息队列
    private  transient LinkedList<InThread> inThreads;
    private  transient InThread first;
    private  transient InThread last;



    public static void main(String args[]){

    }


    class InThread implements Runnable {


        @Override
        public void run() {
            System.out.println("run "+Thread.currentThread().getName());
        }
    }
}
