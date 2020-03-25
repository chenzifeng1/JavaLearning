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
    private static ArrayList<Integer>  integerArrayList = new RandomArray().getRandomArray();

    public MySort() {
    }

    public static void quickSort(){

    }

    /**
     * 寻找数组中的最小值
     * @param values
     * @param <E>
     * @return
     */
    public static <E extends Number&Comparable<? super E>> E min(E[] values){
        if (values == null || values.length ==0) return null;
        E min = values[0];  //min是当前遍历元素的引用，用来指向已遍历过的最小元素
        for (int i = 0;i <values.length;i++){
            if(min.compareTo(values[i])>1) min = values[i]; //此处有问题，因为该泛型的compareTo方法未被重写
        }
        return min;
    }


}
