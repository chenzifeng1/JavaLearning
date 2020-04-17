package knowledge;

public interface General<T> extends TestInterface_1,TestInterface_2 {
    int a = 0;
    //public void setValue(T t); 此处有坑

    T getValue();
}
