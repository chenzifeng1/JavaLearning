package lambda;


/**
 * 函数式接口内只能由一个抽象方法，相当于函数单独存在
 * @FunctionalInterface 注释：不满足函数是接口时报错
 */
@FunctionalInterface
public interface MyService {
    void print(String str);
}
