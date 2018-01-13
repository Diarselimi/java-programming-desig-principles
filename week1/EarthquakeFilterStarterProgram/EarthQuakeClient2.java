import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MagnitudeFilter(4.0, 5.0); 
        //Filter d = new DepthFilter(-35000.0, -12000.0);
        //ArrayList<QuakeEntry> m7  = filter(list, f); 
        //ArrayList<QuakeEntry> m8 = filter(m7, d);
        Filter j = new DistanceFilter(new Location(35.42, 139.43), 10000*10000);
        Filter u = new PhraseFilter(PhraseFilter.END, "Japan");
        ArrayList<QuakeEntry> m7 = filter(list, j);
        ArrayList<QuakeEntry> m8 = filter(m7, u);
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        } 
    }
    
    public void testMatchAllFilter(){ 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        
        String source = "data/nov20quakedata2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilters maf = new MatchAllFilters();
        maf.addFilter(new MagnitudeFilter(1.0, 4.0));
        maf.addFilter(new DepthFilter(-180000.0, -30000.0 ));
        maf.addFilter(new PhraseFilter(PhraseFilter.ANY, "o"));
        
        ArrayList<QuakeEntry> m8 = filter(list, maf);
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        } 
        
        System.out.println("Filter used: "+maf.getName());
        System.out.println("Total quakes: "+m8.size());
    }
    
    public void testMatchAllFilter2(){ 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        
        String source = "data/nov20quakedata2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilters maf = new MatchAllFilters();
        maf.addFilter(new MagnitudeFilter(3.5, 4.5));
        maf.addFilter(new DistanceFilter(new Location(39.7392, -104.9903), 1000*1000));
        //maf.addFilter(new PhraseFilter(PhraseFilter.END, "a"));
        ArrayList<QuakeEntry> m8 = filter(list, maf);
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        } 
        System.out.println("Total quakes found: "+m8.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
