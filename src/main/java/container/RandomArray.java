package container;

import java.util.ArrayList;
import java.util.Random;

public class RandomArray {
    private final int defaultLength = 20;
    private final int seed = 521;
    private final int defaultBound = 1000;

    private ArrayList<Integer> randomArray;
    private Random random = new Random(seed);

    public RandomArray() {
        for (int i = 0; i < defaultBound; i++) {
         //   randomArray.add(Math.abs(random.nextInt(defaultBound)));
        }
    }

    /**
     * This method just generate a array which include some int nums.
     *
     * @param length
     */
    public RandomArray(int length) {
        if(randomArray==null){
            randomArray  = new ArrayList<Integer>();
        }
        if (length < 0) {
            length = defaultLength;
        }
        for (int i = 0; i < length; i++) {
            randomArray.add(Math.abs(random.nextInt(defaultBound)));
        }
    }

    /**
     *
     * @param length
     * @param start
     * @param end
     */
    public RandomArray(int length, int start, int end) {
        int bound = 0;
        if (length < 0) {
            length = defaultLength;
        }
        if (start > end) {
            bound = defaultBound;
        } else {
            bound = end - length + 1;
        }
        for (int i = 0; i < length; i++) {
            randomArray.add(Math.abs(random.nextInt(bound)));
        }
    }

    public ArrayList getRandomArray(){
        if(randomArray.size()==0){
            return new RandomArray().randomArray;
        }
        return this.randomArray;
    }

    public void checkRandonArray(){
        for(int i : randomArray){
            System.out.print(i+' ');
        }
        System.out.println();
    }
}
