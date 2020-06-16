package log;

/**
 * 计算代码执行时间
 */
public class TimeCounter {
    private  long strat = 0;

    public  void timeStart(){
        strat = System.nanoTime();
    }

    public long useTime(){
        if(strat==0)
            return -1;
        return System.nanoTime()-strat;
    }
}
