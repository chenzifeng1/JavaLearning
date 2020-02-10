#java零散知识点总结
> 1.String类 使用final来修饰字符数组来保存字符串，所以String对象不可变
```java
class String{
    @Stable
     private final byte[] value;
}
```
> StringBuffer与StringBuilder类，两者都继承了AbstractStringBuilder类，
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
> 2.泛型声明：