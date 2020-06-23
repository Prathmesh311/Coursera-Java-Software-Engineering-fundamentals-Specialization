
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    public String decrypt (String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex (freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }
    
    public int[] countLetters (String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex (int[] vals) {
        int maxIndex = 0;
        for (int k=0; k < vals.length; k++) {
            // consider maxIndex so vals[maxIndex] rather than maxIndex
            if (vals[maxIndex] < vals[k]) {
                // And k rather than vals[k]
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    
    public String halfOfString (String message, int start) {
        String answer = "";
        for (int k = start; k< message.length() ; k+= 2) {
            answer = answer + message.charAt(k);        
        }
        return answer;
    }
    
    
    public int getKey (String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex (freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        return dkey;
    }
    
    
    public String decryptTwoKeys (String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String firstHalfOfString = halfOfString(encrypted, 0);
        String secondHalfOfString = halfOfString(encrypted, 1);
        int firstDKey = getKey(firstHalfOfString);
        int secondDKey = getKey(secondHalfOfString);
        System.out.println("Found keys are " + firstDKey + ", " + secondDKey);
        String decrypted = cc.encryptTwoKeys(encrypted, 26 - firstDKey
                                            , 26 - secondDKey);
        return decrypted;
    }
    
    
    public void testDecrypt () {
        CaesarCipher cc = new CaesarCipher();
        String answer = "the legion";
        String encryption = cc.encrypt(answer, 23);
        String decryption = decrypt(encryption);
        System.out.println("decryption is " + decryption);
        System.out.println("answer is     " + answer);
    }
    

    public void testHalfOfString () {
        String message = "Qbkm Zgis";
        int start = 0;
        String halfOfString = halfOfString(message, start);
        System.out.println("String is " + message);
        System.out.println("half of String is " + halfOfString);
        
        start = 1;
        halfOfString = halfOfString(message, start);
        System.out.println("String is " + message);
        System.out.println("half of String is " + halfOfString);
    }
    

    public void testDecryptTwoKeys () {
        FileResource fr = new FileResource();
        String s = fr.asString();
        System.out.println("decryption is " + decryptTwoKeys(s));
        
        s = "Top ncmy qkff vi vguv vbg ycpx";
        System.out.println("decryption is " + decryptTwoKeys(s));
        
        s = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        System.out.println("decryption is " + decryptTwoKeys(s));
    }
}

