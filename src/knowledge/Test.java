package knowledge;

import IOStream.MyInput;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
    Generic<Integer> integerGeneric = new Generic<>(10);


    public static void main(String args[]) {
//        Scanner input = new Scanner(System.in);
//        String str = input.nextLine();
        try {
            reflectionTest();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void equalsTest() {
        String a = "ab";
        String b = new String("ab");
        String c = new String("ab");
        System.out.println(a.getClass() + "@" + a.hashCode());
        System.out.println(b.getClass() + "@" + b.hashCode());
        System.out.println(c.getClass() + "@" + c.hashCode());
        if (a == b) {
            System.out.println("a == b is true");
        } else {
            System.out.println("a's address is different with b");
        }
        if (a == c) {
            System.out.println("a == c is true");
        } else {
            System.out.println("a's address is different with c");
        }
        if (a.equals(b)) {
            System.out.println("a equal b is true");
        } else {
            System.out.println("a equal b is false");
        }
        if (a.equals(c)) {
            System.out.println("a equal c is true");
        } else {
            System.out.println("a equal c is false");
        }
    }

    /**
     * 异常类：在try内的返回之前会首先执行finally的代码
     *
     * @param value
     * @return
     */
    public static int throwableTest(int value) {
        try {
            return value * 2;
        } finally {
            if (value == 0)
                return 1;
        }
    }


    public static void reflectionTest() throws ClassNotFoundException,
            IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException,
            NoSuchFieldException {
        Class<?> targetClass = Class.forName("knowledge.Reflection");
        Reflection reflection = (Reflection) targetClass.newInstance();

        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods){
            System.out.println(method.toString());
            System.out.println(method.getName());
        }


        System.out.println("*******************************");
        Method publicMethod = targetClass.getDeclaredMethod("testMethod",String.class);
        publicMethod.invoke(reflection,"chenzifeng");




        Field strField = targetClass.getDeclaredField("str");
        //为了对类中的参数进行修改，取消安全检查
        strField.setAccessible(true);
        //修改
        strField.set(reflection,"czf");

        String strValue = null;
        strField.get(strValue);
        System.out.println(strValue);

        Method privateMethod = targetClass.getDeclaredMethod("privateMethod  ");
        privateMethod.setAccessible(true);
        privateMethod.invoke(reflection);
        System.out.println("success");




    }

}
