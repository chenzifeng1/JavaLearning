package org.algorithm.test.memory;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 内存页，对应磁盘的存储块，存储的基本单位
 * @date 2024/2/28 10:28 PM
 */
public class MemoryPage {

    /**
     * 内存页大小，单位：字节，一旦设定，不允许更改
     */
    private final int pageSize;

    /**
     * 内存页数据存储：对应磁盘的存储块
     */
    private byte[] data;

    /**
     * 内存页是否被使用
     */
    private boolean inUse;

    public MemoryPage(int size) {
        this.pageSize = size;
        this.data = new byte[size];
        this.inUse = false;
    }

    public void clear() {
        this.data = new byte[pageSize];
    }

    public byte[] getData() {
        return this.data;
    }

    public boolean isInUse() {
        return this.inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
