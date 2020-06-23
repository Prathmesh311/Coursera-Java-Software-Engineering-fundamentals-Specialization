
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    
    public void totalBirths()
    {
        FileResource fr = new FileResource();
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalBoysNames = 0;
        int totalGirlsNames = 0;
        int totalNames = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            String gender = rec.get(1);
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(gender.equals("M"))
            {
                totalBoys += numBorn;
                totalBoysNames++;
            }else{
                totalGirls += numBorn;
                totalGirlsNames++;
            }
        }
        totalNames = totalBoysNames + totalGirlsNames;
        totalBirths = totalBoys + totalGirls;
        System.out.println("total births = " + totalBirths);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total names = " + totalNames);
        System.out.println("total boys names = " + totalBoysNames);
        System.out.println("total girls names = " + totalGirlsNames);
    }
    
    public int getRank(int year, String name, String gender)
    {
        String Filename = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(Filename);
        int Rank = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            if(rec.get(1).equals(gender))
            {
                Rank++;
                if(rec.get(0).equals(name))
                {
                    return Rank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender)
    {
        String Filename = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(Filename);
        int Ranking = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            if(rec.get(1).equals(gender))
            {
                Ranking++;
                if(Ranking == rank)
                {
                    String name = rec.get(0);
                    return name;
                }
            }
        }
        return "No Name";
    }
    
    
    public String whatIsNameInYear(String name, int year , int newYear, String gender)
    {
        int rank = getRank(year,name,gender);
        //System.out.println(rank);
        String newName = getName(newYear, rank, gender);
        return newName;
    }
    
    public int yearOfHighestRank(String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        int Ranking = 0;
        int yearOfHighRank = 0;
        
        for (File f : dr.selectedFiles()) 
        {
            String filename = f.getName();
            String year = filename.substring(3,7);
            int currRank = 0;
            
            FileResource fr = new FileResource(f);
            for (CSVRecord rec : fr.getCSVParser(false))
            {
                if(rec.get(1).equals(gender))
                {
                    currRank++;
                    if(rec.get(0).equals(name))
                    {
                        if((Ranking > currRank || Ranking == 0) && (currRank != -1))
                        {
                            Ranking = currRank;
                            yearOfHighRank = Integer.parseInt(year);
                        }
                    }
                }
            }   
        }
        if (yearOfHighRank == 0) {
            return -1;
        }
        return yearOfHighRank;
    }
            
    public double getAverageRank(String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        int rank = 0;
        int numYears = 0;
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            String fileName  = f.getName();
            int year = Integer.parseInt(fileName.substring(3,7));
            int currRank = getRank(year, name, gender);
            if(currRank == -1)
            {
                currRank = 0;
            }
            rank += currRank;
            numYears++;
        }
        double AvgRank = ((double)rank)/numYears;
        return AvgRank;
    }
        
    public int getTotalBirthsRankedHigher(int year, String name, String gender)
    {
        String Filename = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(Filename);
        int totalHighRankBirths = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            String currGender = rec.get(1);
            String currName = rec.get(0);
            if(currGender.equals(gender))
            {
                int births = Integer.parseInt(rec.get(2));
                totalHighRankBirths += births;
                if(currName.equals(name))
                {
                    return (totalHighRankBirths - births);
                }
            }
        }
        return -1;
    }
        
    
    public void testgetRank()
    {
        int year = 1960;
        String name = "Emily";
        String gender = "F";
        int rank = getRank(year,name,gender);
        System.out.println("Rank of " + name + " in " + year + " = " + rank);
        
    }
        
    public void testgetName()
    {
        int year = 1980;
        int rank = 350;
        String gender = "F";
        String name = getName(year, rank, gender);
        System.out.println("Search at " + rank + " rank, gender is " + gender
                            + " in " + year);
        System.out.println("Name  at ranking " + rank + " of " + gender + " in year " + year + " = " + name);
    }
    
    public void testwhatIsNameInYear()
    {
        String name = "Owen";
        int  year = 1974;
        int newYear = 2014;
        String gender = "M";
        String newName = whatIsNameInYear(name ,year, newYear, gender);
        System.out.println(name + " born in " + year + " would be " + newName
                            + " if she was born in " + newYear);
    }
    
    public void testyearOfHighestRank()
    {
        String name = "Mich";
        String gender = "M";
        int yearOfHighestRank = yearOfHighestRank(name, gender);
        System.out.println("name is " + name);
        System.out.println("gender is " + gender);
        System.out.println("year of highest ranking is " + yearOfHighestRank);
    }
    
    public void testgetAverageRank()
    {
        String name = "Susan";
        String gender = "F";
        double avgRank = getAverageRank(name, gender);
        System.out.println("Average Ranking of " + name + " is " + avgRank);
    }
    
    public void testgetTotalBirthsRankedHigher()
    {
        int year = 1990;
        String name = "Emily";
        String gender = "F";
        int totalBirthsHighRank = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("Total Births higher ranked than " + name +  " = " + totalBirthsHighRank);
    }
    
}
