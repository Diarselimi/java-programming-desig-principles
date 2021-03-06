import java.util.*;
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public int indexOf(String[] list, int start, WordGram target, int start) {
        for (int k=start; k<list.length; k++) {
            if (target.equals(list[k])) {
                return k;
            }
        }
        return -1;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length) {
            pos = indexOf(myText, pos, key);
            if(pos == -1) {
                break;
            }
            if (pos + 1 >= myText.length) {
                break;
            }
            follows.add(myText[pos+1]);
            pos ++;
        }
        
        return follows;
    }
    
    public void testIndexOf() {
        String ts = "this is just a test yes this is a simple test";
        String[] text = ts.split(" ");
        System.out.println("this starting at 0 should return pos 0:"+indexOf(text, 0, "this"));
        System.out.println("this starting at 3 should return pos 6:"+indexOf(text, 3, "this"));
        System.out.println("frog starting at 0 should return pos -1:"+indexOf(text, 0, "frog"));
        System.out.println("frog starting at 5 should return pos -1:"+indexOf(text, 5, "frog"));
        System.out.println("this starting at 0 should return pos 9:"+indexOf(text, 2, "simple"));
        System.out.println("test starting at 5 should return pos 10:"+indexOf(text, 5, "test"));
    }
}
