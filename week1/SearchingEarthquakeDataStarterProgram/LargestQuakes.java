import java.util.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData) {
        int maxKey = 0;
     
        for ( int k=0; k < quakeData.size(); k++) {
            if(quakeData.get(k).getMagnitude() > quakeData.get(maxKey).getMagnitude()) {
                maxKey = k;
            }
        }
        
        return maxKey;
        
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        
        if ( howMany > quakeData.size() ) {
            howMany = quakeData.size();
        }
        
        ArrayList<QuakeEntry> copy = quakeData;
        ArrayList<QuakeEntry> res = new ArrayList<QuakeEntry>();
        for(int k=0; k < howMany; k++) {
            int max = indexOfLargest(copy);
            res.add(copy.get(max));
            copy.remove(copy.get(max));
        }
        
        return res;
    }
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> data = getLargest(list, 5);
        
        for ( QuakeEntry qe: data ) {
            System.out.println(qe);
        }
        
        System.out.println("Number of quakes: "+data.size());
    }
    
}
