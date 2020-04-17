package knowledge;


/**
 * 抽象类与接口的区别:
 * 1.接口可以定义成员变量，但是默认为public static final。而 抽象类可以定义private的变量。两者都无法直接实例化。
 *  即接口中的成员变量不能被覆盖，无法改变。对于引用对象来说，引用的指向不能被改变，但是引用对象的内容可以改变。
 * 2.关于多重继承，类只能继承一个父类，可以实现多个接口。而接口可以继承多个接口。
 *  在继承多个接口中存在属性名冲突时，使用产生冲突的属性时必须加上所属的接口
 *  而出现方法名冲突时，不必如此。在实现类中只要写一个同名方法即可。
 * 3. 在jdk1.8中的新特性中，interface中的方法可以带方法体，但是要将其声明为static或者是default。
 *  设置为static的方法可以直接使用 接口名.方法名调用。但是default的方法只能借由实现类的实例化对象来调用
 * 4. 抽象类可以有构造方法，而接口不能有构造方法。但是两者都不能进行实例化
 *
 */

public class AbstractImpl extends AbstractClass {

    @Override
    public Object getValue() {
        return 1;
    }


    @Override
    public String getTestName() {
        return TestInterface_1.testName;
    }
}
