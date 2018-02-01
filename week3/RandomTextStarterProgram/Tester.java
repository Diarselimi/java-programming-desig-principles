import edu.duke.*;
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        System.out.println("Follows for the character t");
        for (String ch: markov.getFollows("t")) {
            System.out.println(ch);
        }
        
        System.out.println("Follows for the character e");
        for (String ch: markov.getFollows("e")) {
            System.out.println(ch);
        }
        
        System.out.println("Follows for the character th");
        for (String ch: markov.getFollows("th")) {
            System.out.println(ch);
        }
    }
    
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
	MarkovOne markov = new MarkovOne();
	markov.setTraining(st);
	
	System.out.println("Characters that follows o "+ markov.getFollows("o").size());
    }
}
