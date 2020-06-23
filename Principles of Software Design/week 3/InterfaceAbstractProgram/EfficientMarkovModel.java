
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int N;
    private HashMap<String, ArrayList<String>> myMap;

    public EfficientMarkovModel(int num) {
        myRandom = new Random();
        N = num;
        myMap = new HashMap<String, ArrayList<String>>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
        buildMap();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index, index+N);
        sb.append(key);

        for(int k=0; k < numChars-key.length(); k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }
    
    
    public void buildMap()
    {
         for(int i=N; i < myText.length(); i++)
         {
            ArrayList<String> follows = new ArrayList<String>();
            String key = myText.substring(i-N, i);
            if (!myMap.containsKey(key)) 
            {
                int pos = 0;
                while (pos < myText.length()-1) 
                {
                    int index = myText.indexOf(key, pos);
                    if (index == -1 || index+key.length() >= myText.length()) 
                    {
                        break;
                    }
                    String follow = myText.substring(index+key.length(), index+key.length()+1);
                    follows.add(follow);
                    pos = index+1;
                }

                myMap.put(key, follows);
            }
        }
    }
        
    @Override
    protected ArrayList<String> getFollows(String key) {
        return myMap.get(key);
    }
    
    public void printHashMapInfo()
    {
        for(String key : myMap.keySet())
        {
            System.out.println(myMap.get(key));
        }
        
        System.out.println("Total keys = " + myMap.size());
        
        int maxLen = 0;
        String maxKey = "";
        for(String key : myMap.keySet())
        {
            if(myMap.get(key).size() > maxLen)
            {
                maxLen = myMap.get(key).size();
                maxKey = key;
            }
        }
        System.out.println("size of largest arraylist = " + maxLen);
        System.out.println("key having maximum size value " + maxKey);
    }
        
    
    

    public String toString() {
        return "EfficientMarkovModel of order " + N;
    }
}
