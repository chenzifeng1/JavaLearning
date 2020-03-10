package designModel.singleton;



/**
 * 双重校验锁实现对象单例（线程安全）
 * uniqueInstance = new Singleton();
 * 实现分3步：
 * 1.创建内存空间
 * 2.初始化uniqueInstance
 * 3.将uniqueInstance指向所分配的内存空间
 * 但是由于JVM具有重排指令的特性，所以可能出现的次序并非1-2-3可能是1-3-2。
 * 指令重排在单线程环境下没有问题，因为这三步执行完都能得到一个指向特定内存的初始化的uniqueInstance.
 * 但是在多线程环境下，假设线程A执行了1，3两步后，线程B执行了第2步，因为线程B通过判断得到uniqueInstance不为null，
 * 所以返回了一个尚未被初始化uniqueInstance。
 */
public class Singleton {

    /**
     * volatile是一个轻量的同步机制，相比synchronized提供的同步机制，valitile不会引起线程上下文的切换和调度，但同步性较差
     */
    public volatile static Singleton uniqueInstance;

    public Singleton() {
    }

    public static Singleton getUniqueInstance(){
        //先判断是否
        if(uniqueInstance == null){

            synchronized (Singleton.class){
                uniqueInstance = new Singleton();
            }

        }
        return uniqueInstance;
    }
}
