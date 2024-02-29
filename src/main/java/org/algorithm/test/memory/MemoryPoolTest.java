package org.algorithm.test.memory;

/**
 * @author chenzifeng
 * @version 1.0
 * @description
 * @date 2024/2/28 10:52 PM
 */
public class MemoryPoolTest {

    public static void main(String[] args) {
//        testMemoryPool();
//        testMemoryPoolCase1();
//        testMemoryPoolCase2();
        testMemoryPoolCase3();
    }

    /**
     * 主流程测试:  100个线程，每个线程分配20个内存块，模拟内存使用
     */
    public static void testMemoryPool() {
        // 创建内存池
        MemoryPool memoryPool = new MemoryPool(5);
        try {
            // 创建内存块
            for (int i = 0; i < 100; i++) {
                Thread thread = new Thread(new MemoryAllocationTask(memoryPool));
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常case1：随机释放非法内存
     */
    public static void testMemoryPoolCase1() {
        MemoryPool memoryPool = new MemoryPool(10);
        memoryPool.deallocate(1);
    }

    /**
     * 异常case2：内存池满，再分配内存
     */
    public static void testMemoryPoolCase2() {
        MemoryPool memoryPool = new MemoryPool(10);
        try {
            for (int i = 0; i < 10; i++) {
                memoryPool.allocate();
            }
            memoryPool.allocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 达到最大内存池阈值
     */
    public static void testMemoryPoolCase3() {
        MemoryPool memoryPool = new MemoryPool(Constants.MAX_CAPACITY);
        try {
            for (int i = 0; i < Constants.MAX_CAPACITY; i++) {
                memoryPool.allocate();
            }
            memoryPool.allocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class MemoryAllocationTask implements Runnable {
        private final MemoryPool memoryPool;

        MemoryAllocationTask(MemoryPool memoryPool) {
            this.memoryPool = memoryPool;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    long pageId = memoryPool.allocate();
                    System.out.println("Thread " + Thread.currentThread().getId() + " allocated page with ID: " + pageId);
                    // Sleep3秒 模拟内存使用
                    Thread.sleep(3000);
                    memoryPool.deallocate(pageId);
                    System.out.println("Thread " + Thread.currentThread().getId() + " deallocated page with ID: " + pageId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
