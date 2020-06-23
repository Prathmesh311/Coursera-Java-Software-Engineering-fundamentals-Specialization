
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key)
    {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0 ;
        
        while(pos < myText.length()-1)
        {
            int index = myText.indexOf(key, pos);
            if(index == -1)
            {
                break;
            }
            if(index == -1 || index+key.length() >= myText.length())
            {
                break;
            }
            String currChar = myText.substring(index+key.length(), index+key.length()+1);
            follows.add(currChar);
            pos = index + key.length();
        }

            
        return follows;
    }

}
