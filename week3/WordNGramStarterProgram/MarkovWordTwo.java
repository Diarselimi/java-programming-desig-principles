
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public int indexOf(String[] list, int start, String key1, String key2) {
        for (int k=start; k<list.length-1; k++) {
            if (key1.equals(list[k]) && key2.equals(list[k+1])) {
                return k;
            }
        }
        return -1;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key = myText[index];
        String key2 = myText[index+1];
        sb.append(key);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key, String key1) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length) {
            pos = indexOf(myText, pos, key, key1);
            if(pos == -1) {
                break;
            }
            if (pos + 2 >= myText.length) {
                break;
            }
            follows.add(myText[pos+2]);
            pos ++;
        }
        
        return follows;
    }
    
    public void testFollows() {
        String ts = "this is just a test yes this is a simple test";
        myText = ts.split(" ");
        System.out.println(getFollows("this", "is"));
        System.out.println(getFollows("just", "a"));
    }

}
