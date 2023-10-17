package algorithm;
import java.util.Arrays;

/**
 * @author chenzifeng
 * @version 1.0
 * @description
 * @date 2023/10/17 3:31 PM
 */
public class Sort {


    public static void main(String[] args) {
        int[] arr = new int[] {8, 12,76,45,62,29};
        insertDirectlySort(arr);

        System.out.println("sort arr: "+Arrays.toString(arr));


    }


    /**
     * 直接插入，每次取出下标元素，插入到合适到位置
     *
     * @param arr
     */
    public static void insertDirectlySort(int[] arr) {

        int length = arr.length;
        if (length <= 1) {
            return;
        }

        for (int i = 1; i < length; i++) {
            int temp = arr[i];
            int j = i;
            for (; j > 0 && temp < arr[j-1]; j--) {
               arr[j] = arr[j-1];
            }
            arr[j] =temp;
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void halfInsertSort(int[] arr) {

    }



    public static void bubblingSort(int[] arr) {

    }

}
