
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovOne extends AbstractMarkovModel {
    
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);
        
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows = getFollows(key);
            
            if(follows.size() == 0)
            {
                break;
            }
            
            int Idx = myRandom.nextInt(follows.size());
            String nextString = follows.get(Idx);
            sb.append(nextString);
            key = nextString;
        }
        
        return sb.toString();
    
    }
    
    public String toString()
    {
        return "MarkovModel of order " + 1;
    }
    
}
