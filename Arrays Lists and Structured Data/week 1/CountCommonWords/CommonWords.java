
/**
 * Write a description of CommonWords here.
 * 
 * @author Prathmesh bhondave 
 * @version 
 */
import edu.duke.*;

public class CommonWords {
    public String[] getCommon()
    {
        FileResource fr = new FileResource("data/common.txt");
        String[] common = new String[20];
        int index = 0;
        for(String s : fr.words())
        {
            common[index] = s;
            index++;
        }
        return common;
    }
    
    public void countWords(FileResource fr,String[] common,int[] counts)
    {
        for(String word : fr.words())
        {
            word = word.toLowerCase();
            for(int i=0; i < common.length; i++)
            {
                if(common[i].equals(word))
                {
                    counts[i] += 1;
                }
            }
        }
    }
    
    public void countShakespeare()
    {
        String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt","likeit.txt", "macbeth.txt", "romeo.txt"};
        //String[] plays = {"errors.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        for(int i=0; i < plays.length; i++)
        {
            FileResource fr = new FileResource("data/" + plays[i]);
            countWords(fr,common,counts);
            System.out.println("Done with " + plays[i]);
        }
        
        for (int i=0; i<common.length; i++)
        {
            System.out.println(common[i] + "\t" + counts[i]);
        }
    }
    
}
