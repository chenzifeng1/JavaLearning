import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: PACKAGE_NAME
 * @author: chenzifeng
 * @description:
 * @create: 2020-06-30 11:22
 **/


public class Test {

    public static void main(String[] args) throws ParseException {
        //language=JSON
//        String time = "202007021136";
//        System.out.println(parseToSecond(time));
        System.out.println(new String[6].length);
    }


    public static java.util.Date parseToSecond(String strTime)
            throws ParseException {
        Date ret = null;
        try {
            ret = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(strTime);
        } catch (Exception var3) {
            ret = new Date();
        }
        return ret;
    }
}
