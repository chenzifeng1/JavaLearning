package threadPool;

public interface MyThreadPool {
    /**
     * 线程池执行方法，将线程提交到线程池
     * @param runnable 接受一个实现了Runnable的类
     */
    void executor(Runnable runnable);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 获取初始线程池大小
     */
    int getInitSize();


}
