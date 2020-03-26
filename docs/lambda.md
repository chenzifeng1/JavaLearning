# lambda表达式

是一种语法糖，代码写起来简单，但是编译器来与之前一样。

lambda相当于JS中闭包的代替  
lambda表达式也是对象，对象类型是函数式接口（functional interface）

四种函数式接口
- 消费型接口：Consumer<T>   无返回值，通过accept()调用  
- 函数型接口：Function<T,R>  接受T类型参数，返回R类型结果  
- 供应型接口：Supplier<T>   T是返回结果的类型，通过get()调用  
- 断言型接口：Predicate<T>  T是接受参数的类型，返回boolean类型，多是测试使用，提过test()方法
