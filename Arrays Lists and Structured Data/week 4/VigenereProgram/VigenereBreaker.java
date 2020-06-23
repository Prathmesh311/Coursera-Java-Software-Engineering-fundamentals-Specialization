import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) 
    {
        //REPLACE WITH YOUR CODE
        StringBuilder slice = new StringBuilder();
       
        for(int i=whichSlice; i < message.length(); i += totalSlices)
        {
            slice.append(message.substring(i,i+1));
        }
        return slice.toString();
        //return "WRITE ME!";
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        
        for(int i=0; i < klength; i++)
        {
            String slicedMessage = sliceString(encrypted,i,klength);
            int currKey = cc.getKey(slicedMessage);
            key[i] = currKey;
        }
            
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        //Part 1
        /*FileResource fr = new FileResource();
        String message = fr.asString();
        int[] keys = tryKeyLength(message, 4, 'e');
        System.out.println(keys);
        VigenereCipher vc = new VigenereCipher(keys);
        String decrypted = vc.decrypt(message);
        System.out.println(decrypted);*/
        
       //Part 2
       /*FileResource fr = new FileResource();
        String message = fr.asString();
       // int[] keys = tryKeyLength(message, 5, 'e');
        
        FileResource file = new FileResource("dictionaries/English");
        HashSet<String> words = readDictionary(file);
        String decrypted = breakForLanguage(message, words);
        System.out.println(decrypted);*/
        
        //Part 3
        FileResource fr = new FileResource();
        String message = fr.asString();
        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese","Spanish"};
        HashMap<String, HashSet<String>> dictionaries = new HashMap<String, HashSet<String>>();
        VigenereBreaker vb = new VigenereBreaker();

        for(String lang : languages)
        {
            FileResource dict = new FileResource("dictionaries/" + lang);
            HashSet<String> words = vb.readDictionary(dict);
            dictionaries.put(lang, words);
            System.out.println("Readed " + lang);
        }
                
        vb.breakForAllLangs(message, dictionaries);
    }
        
        
        
    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> words = new HashSet<String>();
        for(String s : fr.lines())
        {
            String currline = s.toLowerCase();
            words.add(currline);
        }
        return words;
    }
    
    public int countWords(HashSet<String> dictionary, String message)
    {
        int totalWords = 0;
        
        for(String s : message.split("\\w"))
        {
            String currWord = s.toLowerCase();
            if(dictionary.contains(currWord))
            {
                totalWords++;
            }
        }
        return totalWords;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
        int maxMatchWords = 0;
        String bestDecrypt = "";
        //int bestLength = 0;
        
        for(int i=1; i<=100; i++)
        {
            char mostCommonChar = mostCommonCharIn(dictionary);
            int[] keys = tryKeyLength(encrypted, i, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int matchedWords = countWords(dictionary, decrypted);
             
            if(matchedWords > maxMatchWords)
            {
                maxMatchWords = matchedWords;
                //bestLength = i;
                bestDecrypt = decrypted;
            }
        }
        return bestDecrypt;
    }
        
        
    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        HashMap<Character,Integer> charaters = new HashMap<Character,Integer>();
        
        for(String s : dictionary)
        {
            char[] wordArray = s.toLowerCase().toCharArray();
            for(char ch: wordArray)
            {

                if(!charaters.containsKey(ch))
                {
                    charaters.put(ch, 1);
                }
                else
                {
                    charaters.put(ch, charaters.get(ch) + 1);
                }
            }
        }
        
        int maxNum = 0;
        char maxOccure = '\0';
        for(char ch : charaters.keySet())
        {
            int num = charaters.get(ch);
            if(num > maxNum)
            {
                maxNum = num;
                maxOccure = ch;
            }
        }
        return maxOccure;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages)
    {
        int maxMatch = 0;
        String bestLang = "";
        String bestDecrypted = "";
        
        for(String lang : languages.keySet())
        {
            HashSet<String> words = languages.get(lang);
            System.out.println("\n" + "Checking on " + lang + "...");
            String decrypted = breakForLanguage(encrypted, words);
            int matchedWords = countWords(words, decrypted);
            if(matchedWords > maxMatch)
            {
                bestDecrypted = decrypted;
                maxMatch = matchedWords;
                bestLang = lang;

            }
        }
        
        System.out.println("Best Language : " + bestLang + "\n"  + bestDecrypted);
            
    }
        
}
