package algorithm;


import java.util.Scanner;

/**
 * 以字符串的形式读入两个数字，再以字符串的形式输出两个数字的和。
 *
 * 输入描述:
 * 输入两行，表示两个数字a和b，-109 <= a , b <= 109  ，用双引号括起。
 *
 * 输出描述:
 * 输出a+b的值，用双引号括起。
 *
 * 输入例子1:
 * "-26"
 * "100"
 *
 * 输出例子1:
 * "74"
 */
public class Subject_0 {


    public static void main(String[] args){
        String a,b;
        String s;
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        a = s.split(" ")[0];
        b = s.split(" ")[1];
        System.out.println(sum(a,b));
    }

    public static String sum(String a,String b){
        return String.valueOf(Integer.valueOf(a)+Integer.valueOf(b));

    }


}
