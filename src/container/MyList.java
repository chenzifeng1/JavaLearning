package container;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *List接口在Collection的基础上增加了大量方法，使其可以在list中间增加和删除元素
 *  两种基本类型的list
 *      1.ArrayList: 长于随机访问元素
 *          ArrayList 继承自AbstractList类，实现了List<E>, RandomAccess, Cloneable, java.io.Serializable方法
 *          默认容量为10，
 *      2.LinkedList:通过较低代价在List中间增加和删除元素，并且提供了优化的顺序访问
 */
public class MyList {
    private List<Integer> myList;

    public MyList() {
    }

    public MyList(int num) {
        if(num%2==0)
            this.myList = new ArrayList<>();
        else
            this.myList = new LinkedList<>();
    }

    public MyList(List<Integer> myList) {
        this.myList = myList;
    }
}
