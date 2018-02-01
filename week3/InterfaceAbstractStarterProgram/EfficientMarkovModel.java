
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int length;
    private HashMap<String, ArrayList<String>> oldFollows;
    
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        length = n;
        
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        oldFollows = new HashMap<String, ArrayList<String>>();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-length);
        String key = myText.substring(index, index+length);
        sb.append(key);
        for(int k=0; k < numChars-length; k++){
            
            if ( !oldFollows.containsKey(key) ) {
                oldFollows.put(key, getFollows(key));
            }
            ArrayList<String> follows = oldFollows.get(key);
            if (follows.size() > 0) {
                index = myRandom.nextInt(follows.size());
                key = key.substring(1) + follows.get(index);
                sb.append(follows.get(index));
            }
        }
        printHashMapInfo();
        
        return sb.toString();
    }
    
    public String toString() {
        return "EfficientMarkovModel running order "+length;
    }
    
    public void printHashMapInfo() {
        
       System.out.println("Total keys "+oldFollows.keySet().size());
       int largest = 0;
        /**/
           System.out.println("-------------- Printing Keys of length 3 ---------------");
           for (String key: oldFollows.keySet()) {
               if (oldFollows.get(key).size() > largest) {
                   System.out.println(key);
                   largest = oldFollows.get(key).size();
                }
           }
           System.out.println("Largest size is "+largest);
           /*
           System.out.println("-------------- Printing Array Size ---------------");
           for (ArrayList<String> values: oldFollows.values()) {
               System.out.println("Size is "+values.size());
           }
           */
    }
}
