
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    private double minMag;
    private double maxMag;
    
    public MagnitudeFilter(double min, double max)
    {
        minMag = min;
        maxMag = max;
    }
    
    public boolean satisfies (QuakeEntry qe)
    {
        return minMag <= qe.getMagnitude() && qe.getMagnitude() <= maxMag;
    }
    
    public String getName()
    {
        return "magnitude";
    }
}
