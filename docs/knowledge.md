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
#### ==和equals()
==是判断两个对象的地址是否相同，而equals是判断两个对象的内容是否相同。以String对象为例  
```java
public class test{ 
    String str1 = "a";
    String str2 = new String(a);
}
```
对于String而言，对象str1是一个常量，其值a的地址存储在常量池中，而str2存储在系统分配的堆中。
另外对于java来说我们不能得到对象的实际内存地址，因为java中的堆是由JVM管理的，但是我们可以通过hashcode()这个方法来查看两者的地址关系，JVM回通过哈希码在真正的内存堆中给对象分配地址。但是不意味着哈希码相同，对象的地址就相同了。
我们可以看一下string.hashcode()和String.equals()的源码。  
```java
public final class String{
    public int hashCode() {
        int h = hash;
        if (h == 0 && !hashIsZero) {
            h = isLatin1() ? StringLatin1.hashCode(value)
                           : StringUTF16.hashCode(value);
            if (h == 0) {
                hashIsZero = true;
            } else {
                hash = h;
            }
        }
        return h;
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String aString = (String)anObject;
            if (!COMPACT_STRINGS || this.coder == aString.coder) {
                return StringLatin1.equals(value, aString.value);
            }
        }
        return false;
    }

}
```
hashCode() 的作用是获取哈希码，也称为散列码；它实际上是返回一个int整数。这个哈希码的作用是确定该对象在哈希表中的索引位置。hashCode() 定义在JDK的Object.java中，这就意味着Java中的任何类都包含有hashCode() 函数。
在string的hashcode()中，判断编码方式是Latin还是UTF16然后交给对应的类去编码。而在equals中，先判断两者地址是否相同，如果相同那么两者必然相同。若是不相同，继续判断内容是否相同

关于hashcode()的作用：hashset检查插入元素是否重复时首先得到插入对象的。每个Java类都包含hashCode() 函数。但是，仅仅当创建并某个“类的散列表”(关于“散列表”见下面说明)时，该类的hashCode() 才有用



## Java的I/O流
 - 按流向划分分为：输入流和输出流
 - 按操作单元划分：字节流和字符流
 - 按流的角色划分：节点流和处理流
输入流有两种：InputStream(字节流)和Reader(字符流)  
[Input的类](https://github.com/chenzifeng1/JavaLearning/blob/master/src/knowledge/MyInput.java)
```java
public class input{
    public String inputFirstWay(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    
    public String inputSecondWay(){
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); 
        String s = input.readLine(); 
        reuturn s;
    }   
}
```
输出流有两种：OutputStream(字节流)和Writer(字符流)
```java
public class output{
    
}
```

## 浅拷贝和深拷贝
    浅拷贝：对基本数据类型进行值传递，对引用数据类型进行引用传递般的拷贝，此为浅拷贝。
    深拷贝：对基本数据类型进行值传递，对引用数据类型，创建一个新的对象，并复制其内容，此为深拷贝。
    
## 数组拷贝函数
 在Arrays类有关拷贝的函数  
 返回类型：返回一个泛型数组
 参数：
 1. U[] original ：泛型数组，需要拷贝的目的数组
 2. int newLength：拷贝的长度
 3. Class<? extends T[]> newType ：返回的数组类型
  
  
 ```java
public class Arrays{

   public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
        @SuppressWarnings("unchecked")
        T[] copy = ((Object)newType == (Object)Object[].class)
            ? (T[]) new Object[newLength]
            : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0,
                         Math.min(original.length, newLength));
        return copy;
    }  
  
}
```
 