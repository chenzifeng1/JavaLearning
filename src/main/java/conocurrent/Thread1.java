package conocurrent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: conocurrent
 * @author: chenzifeng
 * @description:
 * @create: 2020-06-23 10:45
 **/

public class Thread1 implements Runnable {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD hh:mm:ss");
    @Override
    public void run() {
        System.out.println(simpleDateFormat.format(new Date()));
        System.out.println(Thread.currentThread().getName());
    }
}
