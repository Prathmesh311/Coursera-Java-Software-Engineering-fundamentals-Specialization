
/**
 * Write a description of wordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class wordPlay {
    public boolean isVowel(char ch)
    {
        String vowels = "AEIOUaeiou";
        
        for (int i=0; i<vowels.length(); i++)
        {
            char currChar = vowels.charAt(i);
            if (currChar == ch)
            {
                return true;
            }
        }return false;
    }
        
    public String replaceVowels(String phrase, char ch)
    {
        StringBuilder encrypted = new StringBuilder(phrase);
        for(int i =0; i < encrypted.length(); i++)
        {
            char currChar = encrypted.charAt(i);
            if(isVowel(currChar))
            {
                encrypted.setCharAt(i, ch);
            }
        }
        return encrypted.toString();
    }
                  
    public String emphasize(String phrase, char ch)
    {
        StringBuilder encrypted = new StringBuilder(phrase);
        
        for(int i=0; i < encrypted.length(); i++)
        {
            char currChar = encrypted.charAt(i);
            if(currChar == ch || currChar == Character.toUpperCase(ch))
            {
                if(i%2==0)
                {
                    encrypted.setCharAt(i, '*');
                }
                if(i%2==1)
                {
                    encrypted.setCharAt(i, '+');
                }
            }
        }return encrypted.toString();
    }
    
    
    
    public void testisVowel()
    {
        char ch = 's';
        boolean Is = isVowel(ch);
        System.out.println("Character " + ch + " is vowel = " + Is);
    }
    
    public void testreplaceVowels()
    {
        String phrase = "Hello World";
        char ch = '*';
        String replacedVowels = replaceVowels(phrase, ch);
        System.out.println(phrase);
        System.out.println(replacedVowels);
    }
    
    public void testemphasize()
    {
        String phrase = "Mary Bella Abracadabra";
        char ch = 'a';
        String replaceChar = emphasize(phrase, ch);
        System.out.println(phrase);
        System.out.println(replaceChar);
    }
}
