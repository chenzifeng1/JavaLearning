package algorithm.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @ProjectName: JavaLearning
 * @Package: algorithm.interview
 * @ClassName: Offer3
 * @Author: czf
 * @Description: 输入一个链表，返回链表反序,单向链表
 * @Date: 2020/9/1 9:00
 * @Version: 1.0
 */
public class Offer3 {

    private Node head;
    private Node mark;
    private int length;


    public static void main(String[] args) throws IOException {
        ArrayList<Integer> result = new ArrayList<>();
        Offer3 offer = new Offer3();
        offer.init();
        System.out.println("please input value,and end with #");
        //读取数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String var1 = bufferedReader.readLine();
            if (var1.equals("#")) {
                break;
            } else {
                offer.add(var1);
                offer.setLength(offer.getLength() + 1);
            }
        }
        offer.invert(offer.head);

        Node temp = offer.getHead().getNode();
        for (int i = 0; i < offer.getLength(); i++) {
            System.out.println(temp.getValue());

            temp = temp.getNode();
        }

    }


    public void init() {
        head = new Node("#");
        length = 0;
        mark = head;
    }

    public void add(String value) {
        Node node = new Node(value);
        mark.node = node;
        mark = node;
    }

    /**
     * 通过调转链表引用，实现链表倒叙输出
     * 但是调转时需要注意，需要标记操作节点的前驱和后继
     * 标记前驱是因为单向链表不能由一个节点找到它的前驱，因此需要记住
     * 标记后继是因为当操作节点的引用改变时，如果没有标记后继，后继节点就没有引用指向它，就会丢失
     *
     * @param first 传入第一个节点即可
     */
    public void invert(final Node first) {
        Node t1, t2, t3;
        if (length <= 1) {
            return;
        } else {
            t1 = first;
            t2 = t1.node;
            t3 = t2.node;
            while (t3 != null) {
                if (t1==first){
                    t2.node = null;
                }else {
                    t2.node = t1;
                }
                t1 = t2;
                t2 = t3;
                t3 = t2.node;
                t2.node = t1;
            }
            first.node = t2;
        }

    }

    private class Node {
        private Node node;
        private String value;

        public Node(Node node, String value) {
            this.node = node;
            this.value = value;
        }

        public Node(String value) {
            this.value = value;
            this.node = null;
        }

        public Node() {
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getMark() {
        return mark;
    }

    public void setMark(Node mark) {
        this.mark = mark;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
