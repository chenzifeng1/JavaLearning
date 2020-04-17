package knowledge;

public interface TestInterface_2 {
    String testName = "TestInterface_2";
    MyString myStr = new MyString("myStr");
    String getTestName();

    static String getMyStr(){
        System.out.println(myStr.getString());
        return myStr.getString();
    }

    default void setMyStr(String str){
        myStr.setString(str);
    }
}
