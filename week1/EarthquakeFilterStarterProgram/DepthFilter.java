
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double min;
    private double max;
    public DepthFilter(double min, double max) {
        this.min = min;
        this.max = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if (qe.getDepth() >= min && qe.getDepth() <= max) {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return "Depth Filter";
    }  
}
