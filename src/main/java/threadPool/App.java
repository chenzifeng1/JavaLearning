package threadPool;



import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class App {

    public static void main(String args[]) throws InterruptedException {
        ThreadPoolDemo threadPool = new ThreadPoolDemo(3,5);
        CountDownLatch countDownLatch = new CountDownLatch(5);

        threadPool.execute(new MyTask(countDownLatch,"task1"));
        threadPool.execute(new MyTask(countDownLatch,"task2"));
        threadPool.execute(new MyTask(countDownLatch,"task3"));
        threadPool.execute(new MyTask(countDownLatch,"task4"));
        threadPool.execute(new MyTask(countDownLatch,"task5"));
        countDownLatch.await();
        Thread.sleep(500);
        threadPool.shutdown();
        System.out.println("finished");
    }

    static class MyTask implements Runnable {

        //它其实是作用于线程当中的，它就像一个门栓，一开始是关闭的，所有希望通过该门的线程都需要等待，
        // 然后开始倒计时，当倒计时一到，等待的所有线程都可以通过。
        private CountDownLatch countDownLatch;
        private String name;
        private Random random = new Random();

        public MyTask(CountDownLatch countDownLatch, String name) {
            this.countDownLatch = countDownLatch;
            this.name = name;
        }

        public MyTask() {
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            try{
                countDownLatch.countDown();
                Thread.sleep(random.nextInt(1000));
                System.out.println(name + " over " );

            }catch (InterruptedException e){
                //此处抛出异常，是因为强制销毁线程
                System.out.println(Thread.currentThread().getId()+" sleep interruptedException "
                +Thread.currentThread().isInterrupted());
            }
        }
    }
}
