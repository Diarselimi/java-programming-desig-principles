
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String lastQ1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ")+1);
        String lastQ2 = q2.getInfo().substring(q2.getInfo().lastIndexOf(" ")+1);
        
        if (lastQ1.compareTo(lastQ2) < 0) {
            return -1;
        }
        
        if (lastQ1.compareTo(lastQ2) > 0) {
            return 1;
        }
        
        if (q1.getMagnitude() < q2.getMagnitude()) {
            return -1;
        }
        
        if (q1.getMagnitude() > q2.getMagnitude()) {
            return 1;
        }   
        
        return 0;
    }
}
