
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
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
