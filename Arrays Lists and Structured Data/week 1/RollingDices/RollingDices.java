
/**
 * Write a description of RollingDices here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
public class RollingDices {
    public void simulate(int rolls)
    {
        Random rand = new Random();
        int[] counts = new int[13];
        
        for(int i=0; i < rolls; i++)
        {
            int d1 = rand.nextInt(6);
            int d2 = rand.nextInt(6);
            System.out.println("roll is " + d1 + "+" + d2 + "=" + (d1+d2));
            counts[d1 + d2] += 1;
        }
        
        for(int i=2; i < counts.length; i++)
        {
            System.out.println(i + "\t" + counts[i]);
        }
    }
}
