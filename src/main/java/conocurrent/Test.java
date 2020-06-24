package conocurrent;

import org.omg.CORBA.TIMEOUT;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: conocurrent
 * @author: chenzifeng
 * @description:
 * @create: 2020-06-23 11:00
 **/

public class Test {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 10;
    private static final long KEEP_ALIVE_TIME = 12;
    private static final TimeUnit TIME_UNIT = TimeUnit.HOURS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingDeque<>();

    public static void main(String[] args) throws InterruptedException {
        //Executors的几种获取线程池的实现中，都是调用new ThreadPoolExecutor(...)来实现的
        ExecutorService pool_1 = Executors.newFixedThreadPool(5);
        //通过设定参数来生成线程池
//        ExecutorService pool_2 = new ThreadPoolExecutor(CORE_POOL_SIZE,
//                MAXIMUM_POOL_SIZE,
//                KEEP_ALIVE_TIME,
//                TIME_UNIT,
//                WORK_QUEUE);
        List<Future> result = new ArrayList<>();

        for (int i = 0; i < CORE_POOL_SIZE; i++) {
            Callable callable = new MyCallable();
            Future future = pool_1.submit(callable);
            result.add(future);
        }
        //关闭线程池
        pool_1.shutdown();
//      pool_2.shutdown();

        for (Future f : result) {
            try {
                System.out.println(f.get().toString());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
