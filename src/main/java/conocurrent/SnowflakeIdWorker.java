package conocurrent;

/**
 * @program: conocurrent
 * @author: chenzifeng
 * @description: 雪花算法实现
 * @create: 2020-06-16 15:19
 **/

public class SnowflakeIdWorker {

    /**
     * 初始时间戳 2015-01-01
     */
    private final long twepoch = 1420041600000L;

    /**
     * 机器id位数
     */
    private final long workIdBits = 5L;

    /**
     * 数据标识id位数
     */
    private final long dataCenterIds = 5L;

    /**
     * 最大机器id的值，该唯一算法可以计算出二进制对应位数可以标识的最大十进制
     */
    private final long maxWorkId = -1L ^ (-1L << workIdBits);

    /**
     * 最大数据标识id ，机器id+数据标识id来唯一标记一个节点。两者加起来共10位
     */
    private final long maxDataCenterid = -1L ^ (-1L << dataCenterIds);

    /**
     * 12位序列id
     */
    private final long sequenceBits = 12L;

    /**
     * 机器id左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识左移17位
     */
    private final long dataCenterIdShift = workIdBits + sequenceBits;

    /**
     * 时间戳左移22位
     */
    private final long timeStampShift = dataCenterIds + workIdBits + sequenceBits;

    /**
     * 生成序列掩码
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;
    /**
     * 数据中心ID(0~31)
     */
    private long dataCenterId;
    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;
    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    public SnowflakeIdWorker(long workerId, long dataCenterId) {
        if (workerId > maxWorkId || workerId < 0) {
            throw new IllegalArgumentException(String.format("workId can not be greater than %d or less than 0", workerId));
        }
        if (dataCenterId > maxDataCenterid || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenterId can not be greater than %d or less than 0", dataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }


    public synchronized long nextId() {
        long timeStamp = timeGen();
        //如果当前时间戳小于上次生成id的时间戳，则可能发生了时钟回退。应该抛出对应的异常
        if (timeStamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timeStamp));
        }
        //如果发生在同一毫秒内，则进行排序
        if (lastTimestamp == timeStamp) {
            sequence = (sequence + 1) % sequenceMask;
            //如果毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一毫秒
                timeStamp = tilNextMillis(lastTimestamp);
            }
        }
        //移位运算组装id
        return timeStamp << timeStampShift
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    /**
     * 获取下一毫秒
     *
     * @param lastTimestamp
     * @return
     */
    protected long tilNextMillis(long lastTimestamp) {
        long currentTime = timeGen();
        while (currentTime <= lastTimestamp) {
            currentTime = timeGen();
        }
        return currentTime;
    }


    /**
     * 获取当前时间的毫秒数
     *
     * @return
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }


    public static void main(String[] args) throws InterruptedException {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 10; i++) {
            long id = idWorker.nextId();
            Thread.sleep(1);
            System.out.println(id);
        }
    }
}
