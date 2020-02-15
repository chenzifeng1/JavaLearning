package container;

import java.util.ArrayList;
import java.util.Random;

public class RandomArray  {
    private final int defaultLength = 20;
    private final int seed = 521;
    private final int defaultBound = 1000;

    private ArrayList<Integer> randomArray;
    private Random random = new Random(seed);

    /**
     * This method just generate a array which include some int nums.
     * @param length
     */
    public RandomArray(int length) {
        if (length<0){
            length = defaultLength;
        }
        for (int i =0;i< length;i++){
            int intNum =Math.abs(random.nextInt(defaultBound)) ;
            randomArray.add(intNum);
        }
    }

    public RandomArray(int length,int start,int end) {
        int bound = 0;
        if(length<0){
            length = defaultLength;
        }
        if(start>end){
            bound = defaultBound;
        }
       
    }
}
