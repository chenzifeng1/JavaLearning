package conocurrent;

import java.util.Date;

/**
 * @program: conocurrent
 * @author: chenzifeng
 * @description:
 * @create: 2020-06-23 14:08
 **/

public class Thread2 extends Thread {

    @Override
    public void run() {
        System.out.println(new Date());
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
