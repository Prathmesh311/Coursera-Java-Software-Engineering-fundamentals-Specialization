
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    
    public MarkovModel() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars,int N){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index, index+N);
        sb.append(key);
        
        for(int k=0; k < numChars-N; k++){
            ArrayList<String> follows = getFollows(key);
            
            if(follows.size() == 0)
            {
                break;
            }
            
            int Idx = myRandom.nextInt(follows.size());
            String nextString = follows.get(Idx);
            sb.append(nextString);
            key = key.substring(1)+ nextString;
        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key)
    {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0 ;
        
        while(pos < myText.length()-1)
        {
            int index = myText.indexOf(key, pos);
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
