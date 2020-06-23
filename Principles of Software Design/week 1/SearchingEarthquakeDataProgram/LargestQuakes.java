
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Total Quakes = " + list.size());
        
        int maxMagnitudeIndex = indexOfLargest(list);
        System.out.println("maximum magnitude index is " + maxMagnitudeIndex + 
                            " and magnitude is " + list.get(maxMagnitudeIndex).getMagnitude());
        
        ArrayList<QuakeEntry> largeMag = getLargest(list, 50);
        for(QuakeEntry qe : largeMag)
        {
            System.out.println(qe);
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData)
    {
        int maxIndex = 0;
        double maxMag = 0;
        
        for(int i=0; i < quakeData.size(); i++)
        {
            QuakeEntry qe = quakeData.get(i);
            double mag = qe.getMagnitude();
            if(mag > maxMag)
            {
                maxIndex = i;
                maxMag = mag;
            }
        }
        return maxIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany)
    {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> largeMag = new ArrayList<QuakeEntry>();
        
        for(int i=0; i < howMany; i++)
        {
            int index = indexOfLargest(copy);
            largeMag.add(copy.get(index));
            copy.remove(index);
        }
        return largeMag;
    }
            
}
