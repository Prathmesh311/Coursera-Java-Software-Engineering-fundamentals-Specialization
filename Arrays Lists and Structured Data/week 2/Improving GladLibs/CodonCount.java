
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String,Integer> codonCount;
    
    public CodonCount()
    {
        codonCount = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(String dna, int start)
    {
        codonCount.clear();
        
        for(int i=0; i + 2 < dna.length(); i += 3)
        {
            String codon = dna.substring(i,i+3);
            if(!codonCount.keySet().contains(codon))
            {
                codonCount.put(codon, 1);
            }
            else{
                codonCount.put(codon, codonCount.get(codon) + 1);
            }
        }
    }
    
    public String getMostCommonCodon()
    {
        int maxIndex = 0;
        String maxCodon = "";
        for(String codon : codonCount.keySet())
        {
            if(codonCount.get(codon) > maxIndex)
            {
                maxIndex = codonCount.get(codon);
                maxCodon = codon;
            }
        }
        return maxCodon;
    }
    
    public void printCodonCounts(int start, int end)
    {
        for(String codon : codonCount.keySet())
        {   

            if (start <= codonCount.get(codon) && codonCount.get(codon) <= end)
            {
                System.out.println(codon + "\t" + codonCount.get(codon));
                
            }
        }
    }
    
    public void tester()
    {
        FileResource  fr = new FileResource();
        int start=5;
        int end = 7;
        
        for(String s : fr.lines())
        {
            String DNA = s.toUpperCase().trim();
            for(int i=0; i < 3; i++)
            {
                buildCodonMap(DNA,i);
                System.out.println("reading frame start with " + i + 
                                    " results in " + codonCount.size() + " unique codons");
                
                String maxCodon = getMostCommonCodon();
                System.out.println("and the most common codon is " + 
                                    maxCodon + " with count " + codonCount.get(maxCodon));
                                    
                System.out.println("count for codons between " + start + " and " + end);
                printCodonCounts(start,end);
            }
        }
    }
                                    
}


