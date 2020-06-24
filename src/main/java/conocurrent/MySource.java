package conocurrent;

/**
 * @program: conocurrent
 * @author: chenzifeng
 * @description:
 * @create: 2020-06-23 10:46
 **/

public class MySource {

    private static int LOCK = 0;
    private static String STR_SOURCE = "source";
    /**
     * 对资源的修改次数
     */
    private static int UPDATE = 0;
    /**
     * 读取次数
     */
    private static int READ = 0;

    /**
     * 读取方法可以加共享锁，即读锁
     *
     * @return
     */
    public static String readSource() {
        System.out.println("读取次数:"+ ++READ);
        return STR_SOURCE;
    }

    /**
     * 修改必须写为同步方法
     *
     * @param string
     */
    public static void update(String string) {
        synchronized (STR_SOURCE) {
            System.out.println("开始更新");
            STR_SOURCE = string;
            UPDATE++;
            System.out.println("更新次数："+UPDATE);
        }
    }
    public  static int getLOCK() {
        return LOCK;
    }
    public static  void setLOCK(int LOCK) {
        MySource.LOCK = LOCK;
    }
}
