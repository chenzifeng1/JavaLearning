## 线程
线程所处的基本状态：  
![线程所处的基本状态](../picture/Java线程的状态.png)
线程的状态会随着代码而变化：
![线程状态变迁](../picture/Java%20线程状态变迁.png)

线程创建之后处于 NEW(新建)状态，执行start()方法之后处于Ready(可运行)状态。此状态下的线程在得到cpu时间片的情况下开始执行。
可运行状态的线程获得了 cpu 时间片（timeslice）后就处于 RUNNING（运行） 状态。   
当线程执行 wait()方法之后，线程进入 WAITING（等待）状态。进入等待状态的线程需要依靠其他线程的通知才能够返回到运行状态，而 TIME_WAITING(超时等待) 状态相当于在等待状态的基础上增加了超时限制，比如通过 sleep（long millis）方法或 wait（long millis）方法可以将 Java 线程置于 TIMED WAITING 状态。当超时时间到达后 Java 线程将会返回到 RUNNABLE 状态。当线程调用同步方法时，在没有获取到锁的情况下，线程将会进入到 BLOCKED（阻塞） 状态。线程在执行 Runnable 的run()方法之后将会进入到 TERMINATED（终止） 状态。


### 方法
wait(),notify(),join()，notifyAll()都是很Object的方法，sleep()是Thread的方法。