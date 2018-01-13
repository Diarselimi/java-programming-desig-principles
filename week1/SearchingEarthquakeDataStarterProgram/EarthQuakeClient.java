import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public static final int START = 1;
    public static final int END = 2;
    public static final int ANY = 3;
    
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, int searchIn, String phrase)
    {
        ArrayList<QuakeEntry> res = new ArrayList<QuakeEntry>();
        
        for ( QuakeEntry qe: quakeData ){ 
            switch(searchIn){
                case START:
                    if(qe.getInfo().substring(0, phrase.length()).equals(phrase)) {
                        res.add(qe);
                    }
                    break;
                case END:
                    if(qe.getInfo().substring(qe.getInfo().length() - phrase.length()).equals(phrase)) {
                        res.add(qe);
                    }
                    break;
                default:
                    if(qe.getInfo().contains(phrase)) {
                        res.add(qe);
                    }
                    break;
            }
        }
        
        
        
        return res;
    }
    
    public void quakesByPhrase() {
         EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> quakes = filterByPhrase(list, START, "Explosion");
        for(QuakeEntry qe: quakes) {
            System.out.println(qe);
        }
        
        System.out.println("Total found "+quakes.size()+" with the phrase ");
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData) {
            if(qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> quakes = filterByMagnitude(list, 5.0);
        for(QuakeEntry qe: quakes) {
            System.out.println(qe);
        }

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> quakes = filterByDistanceFrom(list, 1000*1000, city);
        for(QuakeEntry qe: quakes) {
            System.out.println(qe.getLocation().distanceTo(city)/1000.0 + " " + qe.getInfo());
        }
        System.out.println("Found "+quakes.size()+" total");
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData) {
            if(qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                result.add(qe);
            }
        }
        return result;
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> result = filterByDepth(list, -4000.0, -2000.0);
        for(QuakeEntry qe: result) {
            System.out.println(qe);
        }
        System.out.println("Found "+result.size()+" quakes that match the criteria" );
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
