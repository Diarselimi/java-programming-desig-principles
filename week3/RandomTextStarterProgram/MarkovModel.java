
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int length;
    
    public MarkovModel(int n) {
        myRandom = new Random();
        length = n;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
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
            ArrayList<String> follows = getFollows(key);
            
            index = myRandom.nextInt(follows.size());
            key = key.substring(1) + follows.get(index);
            sb.append(follows.get(index));
        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key) {
       ArrayList<String> follows = new ArrayList<String>();
       int startAt = myText.indexOf(key, 0);
       while(startAt != -1 && startAt < ( myText.length() - key.length() )) {
           follows.add(myText.substring(startAt+key.length(), startAt+key.length()+1));
           startAt = myText.indexOf(key, startAt+1);
       }
       return follows;
    }
}
