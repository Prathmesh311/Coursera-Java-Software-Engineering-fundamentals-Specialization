
/**
 * Write a description of testCaeserCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class testCaeserCipherTwo {
    public int[] countLetters(String encrypted)
    {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i=0; i < encrypted.length(); i++)
        {
            char ch = Character.toLowerCase(encrypted.charAt(i));

            int dex = alpha.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
                
    public int maxIndex(int[] freq)
    {
        int maxIndex = 0;
        int maxCount = 0;
        for(int i=0; i < freq.length; i++)
        {
            if(freq[i] > maxCount)
            {
                maxIndex = i;
                maxCount += freq[i];
            }
        }
        return maxIndex;
    }
    
    public String halfOfString(String massage, int start)
    {
        //Stringbuilder massage = new StringBuilder(massage);
        String halfString = "";
        for(int i=start; i < massage.length(); i = i + 2)
        {
            halfString = halfString + massage.charAt(i);
            
        }
        return halfString;
    }
    
    private int getKey (String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex (freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        return dkey;
    }
    public String decryptTwoKeys(String encrypted)
    {

        String half1 = halfOfString(encrypted, 0);
        String half2 = halfOfString(encrypted, 1);
        int key1 = getKey(half1);
        int key2 = getKey(half2);
        System.out.println("Keys found " + key1 +" "+ key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1,key2);
        return cc.encrypt(encrypted);
    }
    
    public void simpleTests()
    {
        
        FileResource fr = new FileResource();
        String massage = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17,8);
        
        String encrypted = cc.encrypt(massage);
        System.out.println("Encrypted Massage is = " + encrypted);
        
        String decrypted1 = cc.decrypt(encrypted);
        System.out.println("Decrypted Massage using cc.decrypt method = " + decrypted1);
        
        String decrypted2 = decryptTwoKeys(encrypted);
        System.out.println("Decrypted massage using breakCaesarCipher method = " + decrypted2);
    }
}
