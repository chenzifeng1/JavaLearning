package knowledge;

public interface TestInterface_1 {
    String testName =new String( "TestInterface_1");

    String getTestName();

    static void setTestName(String testName1){
        System.out.println(testName.toUpperCase());
    }

    default void print(){
        System.out.println("new Name:"+testName);
    }
}
