package org.algorithm.test.memory;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chenzifeng
 * @version 1.0
 * @description
 * @date 2024/2/29 5:12 PM
 */
public class MemoryMessageQueue {
    private final MemoryPool memoryPool;

    /**
     * 队列名 -> 消息队列
     * 消息队列里面存储的是内存页的id
     * 使用BlockingQueue来保证线程安全，可以方便地实现生产者-消费者模式，而无需手动处理线程同步和互斥
     */
    private final ConcurrentHashMap<String, BlockingQueue<String>> queues;
    private final ConcurrentHashMap<String, Long> messageIdMap;

    public MemoryMessageQueue(MemoryPool memoryPool) {
        this.memoryPool = memoryPool;
        this.queues = new ConcurrentHashMap<>();
        this.messageIdMap = new ConcurrentHashMap<>();
    }

    public void createQueue(String queueName) {
        queues.putIfAbsent(queueName, new LinkedBlockingQueue<>());
    }

    public void sendMessage(String queueName, String message) {
        // 获取消息队列，如果队列不存在则创建
        BlockingQueue<String> queue = queues.get(queueName);
        try {
            if (queue != null) {
                send(queue, message);
            } else {
                createQueue(queueName);
                queue = queues.get(queueName);
                send(queue, message);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void send(BlockingQueue<String> queue, String message) throws InterruptedException {
        // 分配内存页
        long pageId = memoryPool.allocate();
        // 写入数据
        MemoryPage page = memoryPool.getPage(pageId);
        byte[] data = page.getData();
        System.arraycopy(message.getBytes(), 0, data, 0, message.getBytes().length);
        // 生成消息id，并放入消息队列中
        String messageId = UUID.randomUUID().toString();
        queue.offer(messageId);
        messageIdMap.put(messageId, pageId);
    }

    public Message receiveMessage(String queueName) {
        // 获取消息队列
        BlockingQueue<String> queue = queues.get(queueName);
        if (!queue.isEmpty()) {
            // 获取消息
            // todo 这里其实可以设置辅助表，当消息被消费ack后，将消息从辅助表中移除，不然其实消息失败之后可能无法消费
            String messageId = queue.poll();
            if (!messageIdMap.containsKey(messageId)) {
                throw new RuntimeException("消息不存在");
            }
            Long pageId = messageIdMap.get(messageId);
            if (pageId != null) {
                MemoryPage page = memoryPool.getPage(pageId);
                byte[] data = page.getData();
                Message message = new Message();
                message.setData(new String(data));
                message.setMessageId(messageId);
                return message;
            }
        }
        return null;
    }

    public void ack(String queueName, String messageId) {
        // 移除消息队列中的消息
        if (messageIdMap.containsKey(messageId)) {
            Long pageId = messageIdMap.get(messageId);
            memoryPool.deallocate(pageId);
            messageIdMap.remove(messageId);
        }
    }

    public static class Message {
        private String messageId;
        private String data;

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

}
