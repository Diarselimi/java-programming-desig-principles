
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {

    private Location location;
    private int max;
    public DistanceFilter(Location loc, int max) {
        this.location = loc;
        this.max = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if ( qe.getLocation().distanceTo(this.location) > this.max) {
            return false;
        }
        return true;
    }
    
    public String getName() {
        return "Distance Filter";
    }  
}
