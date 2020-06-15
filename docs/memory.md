# JVM內存

### OOM(OutOfMemoryException)
1. GC失效:  
报错信息：*org.springframework.web.util.NestedServletException: Handler dispatch failed; nested exception is java.lang.OutOfMemoryError: GC overhead limit exceeded*
报错原因：程序基本耗尽了所有可用内存，GC也无法进行处理。一般而言无法主动GC，JVM会在内存到达一定的阈值之后启动GC。执行垃圾回收的时间比例太大，而有效运算量太小，
默认情况下，GC时间比例超过98%，而回收比例低于2%时，注意该异常只有在执行多次GC却只回收了不到2%的内存时才会抛出。  
此类型OOM的本质原因：  
如果我们了解GC的原理我们就会知道，GC主要执行的内存区域是在堆区，即对象实例所在的内存区域。在执行GC算法的之后，回收的是在

#### 内存泄露与内存溢出
- 内存泄漏:程序运行过程中分配内存给临时变量，用完之后却无法被GC回收。始终占用着内存，既不能被使用也不能被分配给其他程序。
- 内存溢出:程序运行过程中由于程序需要分配的内存大于实际内存，而导致内存不够用的情况。

