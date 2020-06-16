package algorithm;


import container.RandomArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 实现一个基于arrays和list的快速排序
 */
public class MySort {
    Stack<Integer> integerStack = new Stack<Integer>();
    LinkedList linkedList = new LinkedList();
    //private static ArrayList<Integer> integerArrayList = new RandomArray().getRandomArray();

    public MySort() {
    }


    /**
     * 寻找数组中的最小值
     *
     * @param values
     * @param <E>
     * @return
     */
    public static <E extends Number & Comparable<? super E>> E min(E[] values) {
        if (values == null || values.length == 0) return null;
        E min = values[0];  //min是当前遍历元素的引用，用来指向已遍历过的最小元素
        for (int i = 0; i < values.length; i++) {
            if (min.compareTo(values[i]) > 1) min = values[i]; //此处有问题，因为该泛型的compareTo方法未被重写
        }
        return min;
    }


    /**
     * 选择排序
     *
     * @param array
     */
    public static void selectionSort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int min = i; //记录最小值的下标
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            //比较完一趟之后，将最小值与未定位的第一个元素互换
            if (min != i) {
                int tem = array[i];
                array[i] = array[min];
                array[min] = tem;
            }
        }

    }


    /**
     * 冒泡排序
     *
     * @param array
     */
    public static void bubbleSort(int[] array) {
        if (array == null)
            throw new IllegalArgumentException("wrong list");
        boolean flag = false;
        for (int i = 0; i < array.length - 1; i++) {
            flag = false;
            for (int j = i; j < array.length; j++) {
                if (array[j] > array[j + 1]) {
                    int a = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = a;
                    flag = true;
                }
            }
            if (!flag)
                break;
        }

    }


    /**
     * 快速排序  有bug
     *
     * @param array
     * @param l
     * @param r
     */
    public static void quickSort(int[] array, int l, int r) {
        if (l > r)
            return;
        //  记录一下边界下标，将边界最左元素作为key
        int i = l, j = r, key = array[l];
        while (l < r) {
            //依次比较左边元素，直到找到第一个大于key的元素
            while (i < j && array[i] <= key)
                i++;
            //如果是因为array[i] <key 导致循环结束的,那么将下标为i与j两个元素互换，互换之后array[j]必定大于key,故之后的比较不需要在比较该元素
            if(i < j){
                swap(array,i,j);
                j--;
            }
            //依次比较右边元素，直到找到第一个小于key的元素
            while (i < j && array[j] >= key)
                j--;
            if(i<j){
                swap(array,i,j);
                i++;
            }
        }
        //通过上面的比较，key元素应该找到了自己的合适位置，
        System.out.println(l);
        swap(array,i,l);
        quickSort(array,l,i-1);
        quickSort(array,i+1,r);


    }


    public static void swap(int[] array,int i ,int j){
        int tem = array[i];
        array[i] = array[j];
        array[j] = tem;

    }

}
