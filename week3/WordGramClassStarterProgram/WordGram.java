
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        ret = String.join(" ", myWords);
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if (other.length() != this.length()) { return false; }
        
        for (int k=0; k < other.length(); k++) {
            if ( !myWords[k].equals(other.wordAt(k)) ) { return false; }
        }
        return true;

    }

    public WordGram shiftAdd(String word) { 
        
        for (int k=0; k<myWords.length-1; k++) {
            myWords[k] = myWords[k+1];
        }
        
        myWords[myWords.length] = word;
        WordGram out = new WordGram(myWords, 1, myWords.length);
        
        return out;
    }

}