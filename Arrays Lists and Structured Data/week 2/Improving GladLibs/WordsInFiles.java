
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.File;

public class WordsInFiles {
    private HashMap<String ,ArrayList<String>> wordsInFiles;
    
    public WordsInFiles()
    {
        wordsInFiles = new HashMap<String, ArrayList<String>>();
    }
    
    public void addWordsFromFile(File f)
    {
        FileResource fr = new FileResource(f);
        
        for(String word : fr.words())
        {

            if(wordsInFiles.containsKey(word))
            {
                int index = wordsInFiles.get(word).indexOf(f.getName());
                if(index == -1)
                {
                    wordsInFiles.get(word).add(f.getName());
                }
            }
            else
            {
                    wordsInFiles.put(word, new ArrayList<String>());
                    wordsInFiles.get(word).add(f.getName());
                
            }
        }
        System.out.println(wordsInFiles.size());
    }
    
    public void buildWordFileMap ()
    {
        wordsInFiles.clear();
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles())
        {
            addWordsFromFile(f);
        }
    }
        
    public int maxNumber()
    {
        int maxNumber = 0;
        for(ArrayList<String> files : wordsInFiles.values())
        {
            if(files.size() > maxNumber)
            {
                maxNumber = files.size();
            }
        }
        return maxNumber;
    }
        
    public ArrayList<String> wordsInNumFiles(int num)
    {
        ArrayList<String> wordsInNumberOfFiles = new ArrayList<String>();
        for(String word : wordsInFiles.keySet())
        {
            if(wordsInFiles.get(word).size() == num)
            {
                wordsInNumberOfFiles.add(word);
            }
                
        }return wordsInNumberOfFiles;
    }
    
    public void printFilesIn(String word)
    {
        for (String s : wordsInFiles.keySet()) {
            ArrayList<String> fileNamesOfWord = wordsInFiles.get(s);
            if (s.equals(word)) {
                for (String fileName : fileNamesOfWord) {
                    System.out.println(fileName);
                }
            }
        }
    }
    
    public void tester()
    {
        buildWordFileMap();
    
        int maxNumber = maxNumber();
        System.out.println("The greatest number of files a word appears in is "
                            + maxNumber);

        ArrayList<String> wordsInNumFiles = wordsInNumFiles(maxNumber);
        System.out.print("and there are "
                            + wordsInNumFiles.size()
                            + " such words: ");
        for (String s : wordsInNumFiles) {
            System.out.print(" \"" + s + "\" ");
        }
        System.out.print("\n");
        
        for (String s : wordsInNumFiles) {
            System.out.println(" \"" + s + "\" " + " appears in the files: ");
            printFilesIn(s);
            System.out.print("\n");
        }
        
       

        wordsInNumFiles = wordsInNumFiles(4);
        System.out.print("and there are "
                            + wordsInNumFiles.size()
                            + " such words: ");
        for (String s : wordsInNumFiles) {
            System.out.print(" \"" + s + "\" ");
        }
        System.out.print("\n");

        for (String s : wordsInNumFiles) {
            if (s.equals("tree")) {
                System.out.println(" \"" + s + "\" " + " appears in the files: ");
                printFilesIn(s);
                System.out.print("\n");
            }
        }
        
        
        for (String s : wordsInFiles.keySet()) {
            System.out.println("Key: " + "\"" + s + "\" ");
            System.out.print("File Names: ");
            for (String fileName : wordsInFiles.get(s)) {
                System.out.print(" \"" + fileName + "\" ");
            }
            System.out.print("\n\n");
        } 
        
    }
}
