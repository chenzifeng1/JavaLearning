package thread;

import java.text.SimpleDateFormat;
import java.util.Random;

public class ThreadLocalDemo implements Runnable{

    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial( () ->new SimpleDateFormat("yyyy-MM-dd HH:mm"));

    public ThreadLocalDemo(int capacity) {

    }

    public ThreadLocalDemo() {
    }

    public static void main(String args[])throws InterruptedException{
        ThreadLocalDemo obj = new ThreadLocalDemo();
        for(int i =0;i <10 ;i++){
            Thread thread = new Thread(obj,""+i);
            Thread.sleep(new Random().nextInt(1000));
            thread.start();
        }

    }


    @Override
    public void run() {
        System.out.println("Thread name :"+Thread.currentThread().getName()+" default Formatter = "+formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        // 此处改变formatter的值，而formatter是类的静态变量，理论上唯一
        // 但是输出结果看出，即使有线程改变了该变量，但是其他线程访问的时候依然读取的是原来的值。
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread new Name= "+Thread.currentThread().getName()+" formatter = "+formatter.get().toPattern());
    }
}
