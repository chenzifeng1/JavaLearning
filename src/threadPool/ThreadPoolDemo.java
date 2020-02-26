package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {


    public static void main(String args[]){
        ExecutorService executorService = Executors.newScheduledThreadPool(3);
    }
}
