
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
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
    
    public String breakCaesarCipher(String encrypted)
    {
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex (freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        CaesarCipher cc = new CaesarCipher(26 - dkey);
        return cc.encrypt(encrypted);
    }
    
    public void simpleTests()
    {
        FileResource fr = new FileResource();
        String massage = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        
        String encrypted = cc.encrypt(massage);
        System.out.println("Encrypted Massage is = " + encrypted);
        
        String decrypted1 = cc.decrypt(encrypted);
        System.out.println("Decrypted Massage using cc.decrypt method = " + decrypted1);
        
        String decrypted2 = breakCaesarCipher(encrypted);
        System.out.println("Decrypted massage using breakCaesarCipher method = " + decrypted2);
        
    }
    
}
