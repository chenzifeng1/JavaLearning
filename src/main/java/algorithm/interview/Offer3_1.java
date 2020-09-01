package algorithm.interview;

import java.util.ArrayList;

/**
 * @ProjectName: JavaLearning
 * @Package: algorithm.interview
 * @ClassName: Offer3_1
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/9/1 15:56
 * @Version: 1.0
 */
public class Offer3_1 {
    ArrayList<Integer> ret = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode==null){
            return ret;
        }else {
            if (listNode.next!=null){
                printListFromTailToHead(listNode.next);
            }
            ret.add(listNode.val);
        }
        return ret;
    }


    private ArrayList<Integer> invert(ListNode listNode){
        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        if(listNode==null){
            return result;
        }
        while(listNode!=null){
            temp.add(listNode.val);
            listNode = listNode.next;
        }
        for (int i = temp.size()-1; i >=0 ; i--) {
            result.add(temp.get(i));
        }

        while(listNode!=null){
            result.add(listNode.val);
        }
        return result;
    }


    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

}
