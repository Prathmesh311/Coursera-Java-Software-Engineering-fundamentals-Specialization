
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part2 {
    
    public double cgRatio(String dna)
    {
        int count = 0;
        String C = "C";
        String G = "G";
        
        int startIndex = 0;
        int currC = 0;
        while (true)
        {
            currC = dna.indexOf(C, startIndex);
            if (currC == -1)
            {
                break;
            }
            count++;
            startIndex = dna.indexOf(C, startIndex) + 1;
        }
        
        startIndex = 0;
        int currG = 0;
        while(true)
        {
            currG = dna.indexOf(G,startIndex);
            if(currG == -1)
            {
                break;
            }
            count++;
            startIndex = dna.indexOf(G, startIndex) + 1;
        }
     
        return ((double) count)/dna.length();

    }
    
    public int countCTG(String dna)
    {
        int startIndex = 0;
        int count = 0;
        
        while(true)
        {
            int currCTG = dna.indexOf("CTG", startIndex);
            if(currCTG == -1)
            {
                break;
            }
            count++;
            startIndex = dna.indexOf("CTG",startIndex) + 3;
        }
        return count;
    }
    
    
    public void testcgRatio()
    {
        String dna = "ATGCCATAG";
        System.out.println("Testing dna is " + dna);
        double CGRation = cgRatio(dna);
        System.out.println("CG Ration is " + CGRation);
    }
    
    public void testCountCTG()
    {
        String dna = "ATGCCATACTGGCTG";
        System.out.println("Testing dna is " + dna);
        int  numCTG = countCTG(dna);
        System.out.println("number of times CTG comes = " + numCTG);
    }
    
}
