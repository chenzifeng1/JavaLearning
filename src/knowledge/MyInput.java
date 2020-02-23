package knowledge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MyInput {

    /** InputStream：字节流
     * @return
     */
    public static String inputFirstWay(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Reader：字符流
     * @return
     */
    public static String inputSecondWay() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        return input.readLine();
    }

    
}
