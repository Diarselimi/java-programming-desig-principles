
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {

    public static final int START = 1;
    public static final int END = 2;
    public static final int ANY = 3;
    
    private int position;
    private String phrase;
    public PhraseFilter(int position, String phrase) {
        this.position = position;
        this.phrase = phrase;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        switch(this.position) {
            case START:
                return (qe.getInfo().substring(0, phrase.length()).equals(phrase));
            case END:
                return (qe.getInfo().substring(qe.getInfo().length() - phrase.length()).equals(phrase));
            default:
                return qe.getInfo().contains(phrase);
        }
    }
    
    public String getName() {
        return "Phrase Filter";
    }  
    
}
