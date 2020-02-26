package knowledge;

import IOStream.MyInput;

public class Test {
    Generic<Integer> integerGeneric = new Generic<>(10);



    public static void main(String args[]){
//        Scanner input = new Scanner(System.in);
//        String str = input.nextLine();
        MyInput myInput = new MyInput();

        myInput.fileInput("C:\\Users\\Administrator\\Desktop\\test.txt");

    }

    public static void equalsTest(){
        String a = "ab";
        String b = new String("ab");
        String c = new String("ab");
        System.out.println(a.getClass()+"@"+ a.hashCode());
        System.out.println(b.getClass()+"@"+ b.hashCode());
        System.out.println(c.getClass()+"@"+ c.hashCode());
        if(a==b){
            System.out.println("a == b is true");
        }else{
            System.out.println("a's address is different with b");
        }
        if(a==c){
            System.out.println("a == c is true");
        }else{
            System.out.println("a's address is different with c");
        }
        if(a.equals(b)){
            System.out.println("a equal b is true");
        }else {
            System.out.println("a equal b is false");
        }
        if(a.equals(c)){
            System.out.println("a equal c is true");
        }else {
            System.out.println("a equal c is false");
        }
    }

    /**
     * 异常类：在try内的返回之前会首先执行finally的代码
     * @param value
     * @return
     */
    public static int throwableTest(int value){
        try{
            return value*2;
        }finally {
            if(value == 0 )
                return 1;
        }
    }


}
