package org.algorithm.test.memory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chenzifeng
 * @version 1.0
 * @description
 * @date 2024/2/28 10:43 PM
 */
public class MemoryPool {

    /**
     * 模拟内存池
     */
    private final ConcurrentHashMap<Long, MemoryPage> memoryBlocks;

    /**
     * 可用内存块队列
     */
    private final BlockingQueue<Long> availableBlocks;


    public MemoryPool(int initialCapacity) {
        memoryBlocks = new ConcurrentHashMap<>();
        availableBlocks = new LinkedBlockingQueue<>();
        // 初始化内存池
        for (long i = 0; i < initialCapacity; i++) {
            MemoryPage block = new MemoryPage(Constants.DEFAULT_PAGE_SIZE);
            memoryBlocks.put(i, block);
            availableBlocks.add(i);
        }
    }

    /**
     * 申请内存时，会从可用内存块队列中获取一个内存块，并标记为已使用
     */
    public synchronized long allocate() throws InterruptedException {
        if (availableBlocks.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " 申请内存时，可用内存块队列为空,进行扩容");
            // 自动扩容，每次扩容一半
            int size = memoryBlocks.size();
            int newSize = size + (size >> 1) <= Constants.MAX_CAPACITY ? (size >> 1) :
                    Constants.MAX_CAPACITY - size;
            if (newSize == 0) {
                throw new InterruptedException("内存池已满");
            }
            expandPool(size >> 1);
            System.out.println(Thread.currentThread().getName() + " 扩容完成,新容量为:" + memoryBlocks.size());
        }

        long blockId = availableBlocks.take();
        MemoryPage block = memoryBlocks.get(blockId);
        block.setInUse(true);
        return blockId;
    }

    /**
     * 回收内存
     *
     * @param blockId
     */
    public synchronized void deallocate(long blockId) {
        if (!memoryBlocks.containsKey(blockId)) {
            throw new RuntimeException("Illegal blockId");
        }
        MemoryPage block = memoryBlocks.get(blockId);
        if (block.isInUse()) {
            block.setInUse(false);
            availableBlocks.offer(blockId);
        } else {
            System.out.println("this is no use block");
        }


    }


    /**
     * 扩容内存池,
     * 操作系统扩容时，会申请新的内存页，并把旧内存页的内容拷贝到新内存页中
     * 这里为了简单起见，直接追加新内存页
     *
     * @param additionalCapacity
     */
    public synchronized void expandPool(int additionalCapacity) {
        for (int i = 0; i < additionalCapacity; i++) {
            long blockId = memoryBlocks.size();
            MemoryPage block = new MemoryPage(Constants.DEFAULT_PAGE_SIZE);
            memoryBlocks.put(blockId, block);
            availableBlocks.offer(blockId);
        }
    }

    public MemoryPage getPage(long pageId) {
        return memoryBlocks.get(pageId);
    }
}
