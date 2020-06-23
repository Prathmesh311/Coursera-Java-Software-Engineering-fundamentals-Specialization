
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts)
    {
        
        for(String word : resource.words())
        {
            int wordLen = 0;
            for(int i=0; i < word.length(); i++)
            {
                char currChar = word.charAt(i);
                if(Character.isLetter(currChar))
                {
                    wordLen++;
                }
            }
            if(wordLen > counts.length)
            {
                counts[counts.length - 1] += 1;
            }else{
                counts[wordLen] += 1;
            }
        }
        for(int i=0; i < counts.length; i++)
        {
            System.out.println(counts[i] + " words of length " + i);
        }
            
    }
            
    public int indexOfMax(int[] values)
    {
        int maxIndex =0;
        int maxWords = 0;
        for(int i=0; i < values.length; i++)
        {
            if(values[i] > maxWords)
            {
                maxWords = values[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
            
    
    public void testcountWordLengths()
    {
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        int maxNumber = indexOfMax(counts);
        System.out.println("Maximum number of words of length " + counts[maxNumber] + " = " + maxNumber);
    }
}
