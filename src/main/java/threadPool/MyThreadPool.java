package threadPool;

public interface MyThreadPool {
    /**
     * 线程池执行方法，将线程提交到线程池
     * @param runnable 接受一个实现了Runnable的类
     */
    void execute(Runnable runnable);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     *
     * @return 返回线程池初始容量
     */
    int getInitSize();

    /**
     *
     * @return 返回线程池最大容量
     */
    int getMaxSize();

    /**
     *
     * @return 返回核心线程数
     */
    int getCoreSize();

    /**
     * 获取线程池中用于缓存队列的大小
     * @return
     */
    int getQueueSize();

    int getSize();
}
