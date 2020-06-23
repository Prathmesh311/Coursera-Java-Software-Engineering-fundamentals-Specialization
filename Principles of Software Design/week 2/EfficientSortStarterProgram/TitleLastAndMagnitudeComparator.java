
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    
    public int compare(QuakeEntry q1, QuakeEntry q2)
    {
        String q1End = q1.getInfo().substring(q1.getInfo().lastIndexOf(' '));
        String q2End = q2.getInfo().substring(q2.getInfo().lastIndexOf(' '));
        if(q1End.equals(q2End))
        {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        else
        {
            return q1End.compareTo(q2End);
        }
    }
}
