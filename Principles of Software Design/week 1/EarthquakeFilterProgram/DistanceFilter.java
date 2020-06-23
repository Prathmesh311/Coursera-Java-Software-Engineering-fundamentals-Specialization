
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location myLocation;
    private double maxDist;
    
    public DistanceFilter(Location loc, double distance)
    {
        myLocation = loc;
        maxDist = distance;
    }
    
    public boolean satisfies (QuakeEntry qe)
    {
        return qe.getLocation().distanceTo(myLocation) < maxDist;
    }
    
    public String getName()
    {
        return "Distance";
    }
    
}
