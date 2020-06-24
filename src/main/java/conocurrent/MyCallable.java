package conocurrent;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @program: conocurrent
 * @author: chenzifeng
 * @description:
 * @create: 2020-06-23 14:18
 **/

public class MyCallable implements Callable {
    private String id ;
    @Override
    public Object call() throws Exception {
        Date date = new Date();
        id = date.toString()+Thread.currentThread().getName();
        return id;
    }
}
