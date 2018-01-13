
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    
    private double min;
    private double max;
    public MagnitudeFilter(double min, double max) {
        this.min = min;
        this.max = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if( qe.getMagnitude() >= min && qe.getMagnitude() <= max ) {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return "Magnitude Filter";
    }  
}
