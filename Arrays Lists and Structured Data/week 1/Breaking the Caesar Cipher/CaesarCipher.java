
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key)
    {
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabetsCap = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetsLow = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabetsCap = alphabetsCap.substring(key) + alphabetsCap.substring(0,key);
        String shiftedAlphabetsLow = alphabetsLow.substring(key) + alphabetsLow.substring(0,key);
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
       
    public String encryptTwoKeys(String input, int key1, int key2)
    {
        StringBuilder encrypted2Keys = new StringBuilder(input);
        String encryptKey1 = encrypt(encrypted2Keys.toString(), key1);
        String encryptKey2 = encrypt(encrypted2Keys.toString(), key2);
        for(int i=0; i < input.length(); i++)
        {
            char currChar = input.charAt(i);
            
            if(i % 2 == 0)
            {
               char currEncriyptkey1 = encryptKey1.charAt(i);
               encrypted2Keys.setCharAt(i, currEncriyptkey1);
            }
            if(i % 2 != 0)
            {
                char currEncriyptkey2 = encryptKey2.charAt(i);
                encrypted2Keys.setCharAt(i, currEncriyptkey2);
            }
        }
        return encrypted2Keys.toString();
    }
    
    
    
    
    
    public void testencrypt()
    {
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key = 15;
        String encryptedOutput = encrypt(input, key);
        System.out.println(input);
        System.out.println(encryptedOutput);
    }
    
    public void testCeaser()
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23;
        String encrypted = encrypt(message, key);
        System.out.println(message);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public void testencryptTwoKeys(){
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key1 = 21;
        int key2 = 8;
        String twoEncrypt = encryptTwoKeys(input, key1, key2);
        System.out.println(input);
        System.out.println(twoEncrypt);
    }
}

