
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter{
    private double minDepth;
    private double maxDepth;
    
    public DepthFilter(double depthMin, double depthMax)
    {
        minDepth = depthMin;
        maxDepth = depthMax;
    }
    
    public boolean satisfies (QuakeEntry qe)
    {
        return minDepth <= qe.getDepth() && qe.getDepth() <= maxDepth;
    }
    
    public String getName()
    {
        return "Depth";
    }
}
