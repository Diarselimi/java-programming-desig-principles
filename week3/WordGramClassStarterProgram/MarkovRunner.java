
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(3); 
        runModel(markovWord, st, 200, 643); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    
    public void runMarkovWordGram() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(3); 
        runModel(markovWord, st, 200, 643); 
    } 
    
  
    
    public void compareMethods() {
	    FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 100;
		int seed = 42;
		
	    MarkovWord mTwo = new MarkovWord(2);
	    double begin = System.nanoTime();
        runModel(mTwo, st, size, seed);
        double end = System.nanoTime();
        double markovModelTime = (end-begin);
        
	    EfficientMarkovWord mTwoEff = new EfficientMarkovWord(2);
	    begin = System.nanoTime();
	    runModel(mTwoEff, st, size, seed);
	    end = System.nanoTime();
	    double markovEfficientTime = (end-begin);
	    System.out.println("Time for MarkovWord: " + markovModelTime + "\n"
	    + "Time for EfficientMarkovWord: " + markovEfficientTime);
	}

}