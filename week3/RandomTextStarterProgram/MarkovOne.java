
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
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
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows = getFollows(key);
            
            index = myRandom.nextInt(follows.size());
            key = follows.get(index);
            sb.append(key);
        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key) {
       ArrayList<String> follows = new ArrayList<String>();
       int startAt = myText.indexOf(key, 0);
       while(startAt != -1 && startAt < (myText.length()-1)) {
           follows.add(myText.substring(startAt+key.length(), startAt+key.length()+1));
           startAt = myText.indexOf(key, startAt+1);
       }
       return follows;
    }
}
