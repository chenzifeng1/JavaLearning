# java零散知识点总结
#### 1.String  
String使用final来修饰字符数组来保存字符串，所以String对象不可变
```java
class String{
    @Stable
     private final byte[] value;
}
```
> StringBuffer与StringBuilder类，两者都继承了AbstractStringBuilder类，区别是StringBuffer实现父类方法是加了synchronized关键字，对方法使用了同步锁，是线程安全的。

对于三者使用的总结：

 1.操作少量的数据: 适用String  
 2.单线程操作字符串缓冲区下操作大量数据: 适用StringBuilder  
 3.程操作字符串缓冲区下操作大量数据: 适用StringBuffer

```java
abstract class AbstractStringBuilder implements Appendable, CharSequence {
    byte[] value;
    //编码方式分为UTF16和LATIN1
    byte coder;
    int count;
    AbstractStringBuilder() {
    }
    AbstractStringBuilder(int capacity) {
        value = new char[capacity];
    }
    //以String为参数列表的构造方法
    AbstractStringBuilder(String str) {
        int length = str.length();
        int capacity = (length < Integer.MAX_VALUE - 16)
                ? length + 16 : Integer.MAX_VALUE;
        final byte initCoder = str.coder();
        coder = initCoder;
        value = (initCoder == LATIN1)
                ? new byte[capacity] : StringUTF16.newBytesFor(capacity);
        append(str);
    }
}
```   
#### 2.泛型
泛型的三种使用方法：泛型类、泛型接口、泛型方法。泛型的类型参数只能是类类型（包括自定义类），不能是简单类型。  
- 泛型标识Type可以写任意合法的标识号，用来代指指定的泛型类型，但是在泛型类实例化是必须指定泛型的类型。  
  泛型类的声明如下：
```java
public class Generic<Type> {
    private Type value;
    public Generic() {
    }
    public Generic(Type value) {
        this.value = value;
    }
}
```
- 泛型接口与泛型类类似，泛型接口声明如下
```java
public interface General<T> {
    public T getValue();
}
```
-泛型方法比较复杂。（此处借鉴其他人博客上的说明）
1. public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法，只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。  
2. <T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
```java
public class Test<Type>{
    public <T> T genericMethod(Class<T> tClass) throws InstantiationError,
    llegalAccessException{
        T instance = tClass.cast(tClass);
        return instance;
    }
}
``` 
