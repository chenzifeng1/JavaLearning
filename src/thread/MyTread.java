package thread;

public class MyTread implements Runnable{
    private Thread thread;


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
