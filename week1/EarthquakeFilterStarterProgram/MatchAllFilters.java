import java.util.*;
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchAllFilters implements Filter {
    
    private ArrayList<Filter> filters;
    public MatchAllFilters() {
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter filter) {
        this.filters.add(filter);
    }
    
    public boolean diar(){
        return false;
    }
    
    public String getName() {
        String f = "";
        for (Filter filter : filters) {
            f = f+","+filter.getName();
        }
        return f;
    }  
    
    public boolean satisfies(QuakeEntry qe) {
        for(Filter f: filters) {
            if(!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
}
