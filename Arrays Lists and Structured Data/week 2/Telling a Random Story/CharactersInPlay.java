
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> charNames;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay()
    {
        charNames = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    public void update(String person)
    {
        int index = charNames.indexOf(person);
        if(index == -1)
        {
            charNames.add(person);
            counts.add(1);
        }else
        {
            int value = counts.get(index);
            counts.set(index, value + 1);
        }
    }
    
    public void findAllCharacters()
    {
        charNames.clear();
        counts.clear();
        FileResource fr = new FileResource();
        
        for(String s : fr.lines())
        {
            int indexOfPeriod = s.indexOf(".");
            if(indexOfPeriod != -1)
            {
                String person = s.substring(0, indexOfPeriod).trim();
                update(person);
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2)
    {
        for(int i=0; i < counts.size(); i++)
        {
            int numOfSpeakingparts = counts.get(i);
            if(numOfSpeakingparts >= num1 && numOfSpeakingparts <= num2)
            {
                System.out.println(charNames.get(i) + "\t" + counts.get(i));
            }
        }
    }
    
    public void testfindAllCharacters()
    {
        findAllCharacters();
        for(int i=0; i < charNames.size(); i++)
        {
            System.out.println(charNames.get(i) + "\t" + counts.get(i));
        }
        System.out.println("\n");
        charactersWithNumParts(5, 10000);
    }
        
    
}
