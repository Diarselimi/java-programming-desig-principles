
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int largestIndex = from;
        for( int i=from; i < quakeData.size(); i++ ) {
            if (quakeData.get(i).getDepth() > quakeData.get(largestIndex).getDepth()) {
                largestIndex = i;
            }
        }
        return largestIndex;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        int largest = 0;
        for(int k=0; k < 50; k++) {
            largest = getLargestDepth(in, k);
            QuakeEntry largestVal = in.get(largest);
            in.set(largest, in.get(k));
            in.set(k, largestVal);
        }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted ) {
        for (int k=0; k<quakeData.size()-numSorted; k++) {
            if (k+1 < quakeData.size()-numSorted && quakeData.get(k).getMagnitude() >  quakeData.get(k+1).getMagnitude() ) {
                //the actual swaping happens here
                QuakeEntry qe = quakeData.get(k);
                quakeData.set(k, quakeData.get(k+1));
                quakeData.set(k+1, qe);
            }
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> in) {
        for (int k=0; k < in.size(); k++) {
            if ( k+1 < in.size() && in.get(k).getMagnitude() > in.get(k+1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakeData) {
        for (int k=0; k<quakeData.size(); k++) {
            onePassBubbleSort(quakeData, k);
        }
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakeData) {
        for (int k=0; k<quakeData.size(); k++) {
            onePassBubbleSort(quakeData, k);
            if (checkInSortedOrder(quakeData)) {
                System.out.println("Passes to sort "+(k+1));
                break;
            }   
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        for (int i=0; i<in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            if (checkInSortedOrder(in)) {
                System.out.println("Passes to sort "+(i+1));
                break;
            }
             
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByLargestDepth(list);
        //sortByMagnitudeWithCheck(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
