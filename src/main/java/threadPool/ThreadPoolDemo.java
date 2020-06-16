package threadPool;

import java.util.LinkedList;
import java.util.List;

public class ThreadPoolDemo implements MyThreadPool{
    private int corePoolNum; //核心线程数
    private int maxPoolNum = 10;  //最大线程数   默认为10
    private int keepAliveTime;


    private WorkerThread[] workerThreads; //核心线程：工作线程

    //线程队列，负责分配任务
    private transient List<Runnable> inThreads;


    public ThreadPoolDemo(int corePoolNum, int maxPoolNum) {
        if (corePoolNum > maxPoolNum)
            throw new IllegalArgumentException("corePoolNum should be less than maxPollNum");
        this.corePoolNum = corePoolNum;
        this.maxPoolNum = maxPoolNum;

        this.inThreads = new LinkedList<>();
        //先初始化核心线程
        this.workerThreads = new WorkerThread[corePoolNum];
        init();

    }

    /**
     * 线程池将任务配发给线程
     *
     * @param task
     */
    @Override
    public void execute(Runnable task) {

        synchronized (inThreads) {
            inThreads.add(task);
            // notify() :唤醒该对象等待线程的监视器，如果该对象有线程处于等待状态，则唤醒
            inThreads.notify();
        }
    }


    /**
     * 销毁线程池
     */
    public void shutdown() {
        for (int i = 0; i < corePoolNum; i++) {
            workerThreads[i].cancel();
            workerThreads[i] = null;
        }
        inThreads.clear();
    }

    @Override
    public int getInitSize() {
        return 0;
    }

    @Override
    public int getMaxSize() {
        return maxPoolNum;
    }

    @Override
    public int getCoreSize() {
        return corePoolNum;
    }

    @Override
    public int getQueueSize() {
        return workerThreads.length;
    }

    @Override
    public int getSize() {
        return 0;
    }

    private void init() {
        if (corePoolNum != 0) {

            for (int i = 0; i < corePoolNum; i++) {
                workerThreads[i] = new WorkerThread();
                //这里只调用start方法，是因为没有接到任务，所以核心线程不需要run，只需要初始化然后等待任务即可
                workerThreads[i].start();
            }
        }
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < maxPoolNum;
    }


    private class WorkerThread extends Thread {

        private String name;
        private volatile boolean on = true;

        @Override
        public void run() {
            Runnable task = null;
            try {
                //查看任务是否可以执行，且线程没有被中断
                while (on && !interrupted()) {
                    synchronized (inThreads) {
                        //如果任务队列为空，则等待
                        while (on && !interrupted() && inThreads.isEmpty()) {
                            inThreads.wait(1000);
                        }
                        //当任务队列不为空时，移出任务分配给线程执行
                        if (on && !interrupted() && !inThreads.isEmpty()) {
                            task = inThreads.remove(0);
                        }

                        if (task != null) {
                            task.run();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //任务执行完毕，手动删除线程
            task = null;
        }


        void cancel() {
            on = false;
            interrupt(); //中断当前线程
        }
    }


}
