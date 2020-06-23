
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreq;
    
    public WordFrequencies()
    {
        myWords = new ArrayList<String>();
        myFreq = new ArrayList<Integer>();
    }
    
    public void findUnique()
    {
        FileResource fr = new FileResource();
        myWords.clear();
        myFreq.clear();
        for(String s : fr.words())
        {
            String word = s.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1)
            {
                myWords.add(word);
                myFreq.add(1);
            }
            else
            {
                int value = myFreq.get(index);
                myFreq.set(index, value + 1);
            }
        }
    }
    
    public int findIndexOfMax()
    {
        int maxIndex = 0;
        int maxValue = 0;
        for(int i=0; i < myFreq.size(); i++)
        {
            if(myFreq.get(i) > maxValue)
            {
                maxIndex = i;
                maxValue = myFreq.get(i);
            }
        }
        return maxIndex;
    }
    
    
    public void testfindUnique()
    {
        findUnique();
        for(int i=0; i < myWords.size(); i++)
        {
            System.out.println(myWords.get(i) + "\t" + myFreq.get(i));
        }
        System.out.println("Total words " + myWords.size());
    }
            
    public void testfindIndexOfMax()
    {
        findUnique();
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are:" + myWords.get(maxIndex) + " " + myFreq.get(maxIndex));
    }
            
}
