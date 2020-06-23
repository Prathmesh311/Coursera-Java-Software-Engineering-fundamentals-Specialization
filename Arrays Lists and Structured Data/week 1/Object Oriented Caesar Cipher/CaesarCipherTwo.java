
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipherTwo {
    private CaesarCipher cc1;
    private CaesarCipher cc2;
    
    public CaesarCipherTwo(int key1, int key2)
    {
        cc1 = new CaesarCipher(key1);
        cc2 = new CaesarCipher(key2);
    }
    
    public String encrypt(String input)
    {
        StringBuilder encrypted2Keys = new StringBuilder(input);
        String encryptKey1 = cc1.encrypt(encrypted2Keys.toString());
        String encryptKey2 = cc2.encrypt(encrypted2Keys.toString());
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
    
    public String decrypt (String input) {
        StringBuilder decryptedTwoKeys = new StringBuilder(input);
        String decryptedKey1 = cc1.decrypt(input);
        String decryptedKey2 = cc2.decrypt(input);
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                char decryptedKey1Char = decryptedKey1.charAt(i);
                decryptedTwoKeys.setCharAt(i, decryptedKey1Char); 
            }
            else {
                char decryptedKey2Char = decryptedKey2.charAt(i);
                decryptedTwoKeys.setCharAt(i, decryptedKey2Char); 
            }
        }
        return decryptedTwoKeys.toString();
    }
}
