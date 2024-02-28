package org.algorithm.thread;


/**
 * @author chenzifeng
 * @version 1.0
 * @description 题目：使用两个线程交替打印从 1 到 n 的所有奇数和偶数，其中一个线程打印奇数，另一个线程打印偶数，保证输出的奇偶数是按照顺序交替的。
 * @date 2024/2/27 11:35 PM
 */
public class PrintOddEven {
    private static final Object lock = new Object();
    private static int num = 0;
    private static int n = 10;


    public static void main(String[] args) {
        Thread oddThread = new Thread(PrintOddEven::printOdd);
        Thread evenThread = new Thread(() -> {

            printEven();
        });
        oddThread.start();
        evenThread.start();
    }

    public static void printOdd() {
        while (num <= n) {
            synchronized (lock) {
                if (num % 2 == 0) {
                    System.out.println(num);
                    num++;
                    lock.notify();
                    System.out.println("Odd--- lock");
                } else {
                    try {
                        lock.wait();
                        System.out.println("Odd--- release lock");
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }

    }

    public static void printEven() {
        while (num <= n) {
            synchronized (lock) {
                if (num % 2 == 1) {
                    System.out.println(num);
                    num++;
                    lock.notify();
                    System.out.println("Even--- lock");
                } else {
                    try {
                        lock.wait();
                        System.out.println("Even--- release lock");
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }

            }

        }
    }
}
