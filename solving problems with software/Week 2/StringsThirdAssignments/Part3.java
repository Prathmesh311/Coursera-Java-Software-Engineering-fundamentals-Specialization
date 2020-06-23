
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part3 {
    public void processGenes(StorageResource sr)
    {
        int startIndex = 0;
        int count60 = 0;
        int countCG = 0;
        int longestLength = 0;
        int numGenes = 0;
        
        for (String gene : sr.data())
        {
            if(gene.length() > 60)
            {
                System.out.println("Gene Greater than 60 characters = " + gene);
                count60++;
            }
            
            if(cgRatio(gene) > 0.35)
            {
                System.out.println("Gene with CGRation Greater than 0.35 = " + gene);
                countCG++;
            }
            
            if (gene.length() > longestLength)
            {
                longestLength = gene.length();
            }
            numGenes++;
           
        }
        System.out.println("Number of Genes greater than 60 characters = " + count60);
        System.out.println("Number of Genes having CGRation greater than 0.35 = " + countCG);
        System.out.println("Longest Gene Length = " + longestLength);
        System.out.println("Number of total Genes = " + numGenes);
    }
    
    
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
    
    
    
    public void testProcessGenes()
    {
        Part1 P1 = new Part1();
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource sr = P1.getAllGenes(dna);
        int numCTG = P1.countGenes(dna);
        System.out.println("Number of CTG = " + numCTG);
        processGenes(sr);
    }
        
}
