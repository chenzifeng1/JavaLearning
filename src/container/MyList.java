package container;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *List接口在Collection的基础上增加了大量方法，使其可以在list中间增加和删除元素
 *  两种基本类型的list
 *      1.ArrayList: 长于随机访问元素
 *          ArrayList 继承自AbstractList类，实现了List<E>, RandomAccess, Cloneable, java.io.Serializable方法
 *          默认容量为10
 *      2.LinkedList:通过较低代价在List中间增加和删除元素，并且提供了优化的顺序访问 
 */


public class MyList {
    private static final int seed = 822;//随机数种子

    private List<Integer> myList;
    private Random  random;

    public MyList() {
    }
    
    public MyList(int num,int kind) {   //标志耦合，耦合度太高
        if (num<=0)
            return;
        if(kind%2==0)
            this.myList = new ArrayList<>();
        else
            this.myList = new LinkedList<>();
        random.setSeed(seed);
        while((num--)>0){
            myList.add(random.nextInt());
        }
    }


    public MyList(List<Integer> myList) {
        this.myList = myList;
    }
}
