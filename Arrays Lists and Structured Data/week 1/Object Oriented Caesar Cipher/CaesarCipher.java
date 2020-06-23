
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
 
public class CaesarCipher {
    private String alphabetsCap;
    private String alphabetsLow;
    private String shiftedAlphabetsCap;
    private String shiftedAlphabetsLow;
    private int mainKey;
    
    public CaesarCipher(int key)
    {
        String alphabetsCap = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetsLow = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabetsCap = alphabetsCap.substring(key) + alphabetsCap.substring(0,key);
        String shiftedAlphabetsLow = alphabetsLow.substring(key) + alphabetsLow.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input)
    {
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i=0; i < encrypted.length(); i++)
        {
            char currChar = encrypted.charAt(i);
            
            if(Character.isLowerCase(currChar))
            {
                int index = alphabetsLow.indexOf(currChar);
                if(index != -1)
                {
                    char newChar = shiftedAlphabetsLow.charAt(index);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else
            {
                int index = alphabetsCap.indexOf(currChar);
                if(index != -1)
                {
                    char newChar = shiftedAlphabetsCap.charAt(index);
                    encrypted.setCharAt(i, newChar);
                }
            }
                
            
        }
        return encrypted.toString();
    }
    
    public String decrypt (String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
    
    
}
