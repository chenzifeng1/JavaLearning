package conocurrent;

/**
 * @program: conocurrent
 * @author: chenzifeng
 * @description:
 * @create: 2020-06-23 09:54
 **/

public class  SynchronizedLearn {
    private static  int sourceNum = 0;
    private int source = 0;

    public  synchronized void readSourceNum(){
        System.out.println(Thread.currentThread().getName()+" : "+sourceNum++);
    }

    public  void readSource(){
        System.out.println(Thread.currentThread().getName()+" : "+sourceNum++);
    }

    public static void main(String[] args){
        SynchronizedLearn synchronizedLearn = new SynchronizedLearn();
        synchronized (synchronizedLearn){
            synchronizedLearn.setSource(2);
        }
        synchronized (SynchronizedLearn.class){
            SynchronizedLearn.setSourceNum(2);
        }

    }


    public static class TestTread implements Runnable{
        @Override
        public void run() {
            new SynchronizedLearn().readSourceNum();
        }
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public static int getSourceNum() {
        return sourceNum;
    }

    public static void setSourceNum(int sourceNum) {
        SynchronizedLearn.sourceNum = sourceNum;
    }
}
