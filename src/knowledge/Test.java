package knowledge;

public class Test {
    Generic<Integer> integerGeneric = new Generic<>(10);


    public static void main(String args[]){
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

}
