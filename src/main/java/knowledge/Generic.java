package knowledge;

/**
 * 泛型类声明：泛型标志Type可以是任何合法字符，但是要保证上下一致
 * There are some methods to introduce what is generic.There is a generic object in first method ,but it is not a generic method
 * we must ues a sign '<T>' which is between #public and #classname to state a way as a generic method.such as the second
 * method{@code public <T> Boolean compareToValue(Generic<Type> generic,T t);}.we can use two different generic value through state
 * a generic method. well,i have already not know what i say!
 * @param <Type>
 * @author chenzifneg1
 */
public class Generic<Type>  implements General{
    private Type value;
    public Generic() {
    }
    public Generic(Type value) {
        this.value = value;
    }
    //该方法返回类型是该泛型类的泛型标识Type,但是该方法并不是一个泛型方法，尽管它使用了泛型对象

    public Type getValue(Generic<Type> generic){
        return generic.value;
    }
    //该泛型方法返回类型是Bool
    public <T> Boolean compareToValue(Generic<Type> generic,T t){
        System.out.println(t.getClass().toString());
        return generic.value.equals(value);
    }

    public <T> T genericMethod(Class<T> tClass) throws InstantiationError,
            IllegalAccessException{
        T instance = tClass.cast(tClass);
        return instance;
    }

    @Override
    public Object getValue() {
        return a;
    }

    @Override
    public String getTestName() {
        return null;
    }

}
