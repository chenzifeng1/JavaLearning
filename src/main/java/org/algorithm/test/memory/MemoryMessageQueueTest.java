package org.algorithm.test.memory;

/**
 * @author chenzifeng
 * @version 1.0
 * @description
 * @date 2024/2/29 9:55 PM
 */
public class MemoryMessageQueueTest {
    public static void main(String[] args) {
        MemoryPool memoryPool = new MemoryPool(100);
        MemoryMessageQueue messageQueue = new MemoryMessageQueue(memoryPool);

        // 创建"A-QUEUE"队列
        messageQueue.createQueue("A-QUEUE");

        // 创建并启动两个生产者线程
        for (int i = 0; i < 2; i++) {
            Thread producerThread = new Thread(new ProducerTask(messageQueue, "A-QUEUE"));
            producerThread.start();
        }

        // 创建并启动一个消费者线程
        Thread consumerThread = new Thread(new ConsumerTask(messageQueue, "A-QUEUE"));
        consumerThread.start();

        while (true) {
        }
    }

    static class ProducerTask implements Runnable {
        private final MemoryMessageQueue messageQueue;
        private final String queueName;

        ProducerTask(MemoryMessageQueue messageQueue, String queueName) {
            this.messageQueue = messageQueue;
            this.queueName = queueName;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                String message = "Message from Producer " + Thread.currentThread().getId() + ": " + i;
                messageQueue.sendMessage(queueName, message);
                System.out.println("Producer " + Thread.currentThread().getId() + " sent: " + message);
            }
        }
    }

    static class ConsumerTask implements Runnable {
        private final MemoryMessageQueue messageQueue;
        private final String queueName;

        ConsumerTask(MemoryMessageQueue messageQueue, String queueName) {
            this.messageQueue = messageQueue;
            this.queueName = queueName;
        }

        @Override
        public void run() {
            while (true) {
                MemoryMessageQueue.Message message = messageQueue.receiveMessage(queueName);
                if (message != null) {
                    System.out.println("Consumer received: " + message.getData());
                    messageQueue.ack(queueName, message.getMessageId());
                } else {
                    try {
                        System.out.println("Consumer wait message");
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
