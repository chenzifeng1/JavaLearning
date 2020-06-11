# 并发问题详解
### 三个特性
1. 原子性
2. 可见性
3. 有序性

### 线程安全的容器
Vector, HashTable,CopyOnWriteArrayList,



### AQS(AbstractQueueSynchronizer)
介绍：用来构建锁和同步的器的框架，使用AQS能简单高效地构造出众多的同步器。如ReentrantLock，Semaphore  
核心思想：如果请求的共享资源空闲，则将请求的线程设为有效的工作线程，并将共享资源加锁。如果请求的共享资源被占用，
那么就需要一套线程阻塞等待及唤醒时锁分配机制，这个机制AQS是基于CLH队列锁实现的，即将暂时获取不到锁的线程加到队列中。
- CLH队列是一个虚拟双向队列，所谓虚拟是值该队列并没有实例，有的只是队列节点的关联。AQS将每个请求共享资源的线程封装成CLH队列中的节点来实现

AQS通过一个int成员变量表示同步状态，通过内置的FIFO队列来完成资源线程的排队工作。AQS使用CAS(CompareAndSwap)来原子地完成对该值的改变。

AQS对资源的共享方式：
1. Exclusive(独占):只有一个线程能执行，如ReentrantLock。又可以分为公平锁和非公平锁  
a. 公平锁：按照线程在队列中排队顺序，先进队列的线程先拿到锁  
b. 非公平锁：当线程需要锁时，无视队列顺序去进行锁争夺，谁抢到锁谁使用资源 
2. Share(共享)：多个线程可以同时执行，如Semaphore/CountDownLatch

AQS底层使用了**模板方法模式**：如果需要自定义同步器方法步骤一般是这样的：
1. 继承者继承AbstractQueueSynchronizer，对指定方法进行重写。
2. 将AQS组合在自定义同步组件的实现中，并调用其模板方法，模板方法会自动调用重写的方法。

**自定义同步器时需要重写以下方法**
```
isHeldExclusively()//该线程是否正在独占资源。只有用到condition才需要去实现它。
tryAcquire(int)//独占方式。尝试获取资源，成功则返回true，失败则返回false。
tryRelease(int)//独占方式。尝试释放资源，成功则返回true，失败则返回false。
tryAcquireShared(int)//共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
tryReleaseShared(int)//共享方式。尝试释放资源，成功则返回true，失败则返回false。
```
以ReentrantLock为例，state初始化为0，表示未锁定状态。A线程lock()时，
会调用tryAcquire()独占该锁并将state+1。此后，其他线程再tryAcquire()时就会失败，
直到A线程unlock()到state=0（即释放锁）为止，其它线程才有机会获取该锁。
当然，释放锁之前，A线程自己是可以重复获取此锁的（state会累加），这就是可重入的概念。
但要注意，获取多少次就要释放多么次，这样才能保证state是能回到零态的。


### ReentrantLock
可重入锁:一个线程获得了锁之后仍然可以反复的加锁，不会出现自己阻塞自己的情况。  
ReentrantLock默认是非公平锁，因为非公平锁的效率比公平锁搞。

### ThreadLocal
ThreadLocal中存在一个内部静态类ThreadLocalMap，