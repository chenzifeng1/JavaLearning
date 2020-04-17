package knowledge;

/**
 * java.md 反射机制：
 */

public class Reflection {
    private int num;
    private String str;

    public Reflection(int num, String str) {
        this.num = num;
        this.str = str;
    }

    public Reflection() {
    }

    public int getNum() {
        if (num != 0)
        System.out.println(num);
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void testMethod(String str){
        System.out.println("this invoke method:"+str);
    }

    private void privateMethod(){
        System.out.println("str = "+str);

    }

    public String getClassName(){
        return this.getClass().toString();
    }

    public static String  getName(){
        return "Reflection";
    }
    
}
