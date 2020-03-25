package algorithm;

/**
 * java数组实现栈
 * 知识点：泛型数组和类无法直接初始化，因为编译器在编译时需要获取T的类型，而T的类型在编译时就被擦除了。故 new T[n]和new T()都会报错
 */
public class MyStack<T> {
    private transient Object[] values; //模仿ArrayList，Object是基本类，所以可以承载所有类
    private int head;
    private static final int DEFAULT_LENGTH = 10;
    private int size = 0;

    private Object[] DEFAULT_EMPTY;

    public MyStack() {
    }

    public MyStack(int initCapacity) {
        if (initCapacity < 0)
            throw new IllegalArgumentException("the initCapacity should be more than 0");
        this.values = new Object[initCapacity];
    }

}
