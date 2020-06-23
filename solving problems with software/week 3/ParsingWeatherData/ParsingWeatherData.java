
/**
 * Write a description of ParsingWeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeatherData {
    
    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currRow : parser)
        {

            if( coldestSoFar == null)
            {
                coldestSoFar = currRow;
            }
            else{
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));

                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                
                if (coldestTemp > currTemp)
                {
                    coldestSoFar = currRow;
                }
            }
        }
        return coldestSoFar;
    }
    
    public String fileWithColdestTemperature()
    {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar = null;
        String coldestDay = "";
        
        for (File f : dr.selectedFiles()) 
        {
           FileResource fr = new FileResource(f);
           CSVRecord currRow = coldestHourInFile(fr.getCSVParser());
           
           if( coldestSoFar == null)
            {
                coldestDay = f.toString();
            }
            else{
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                
                if (coldestTemp > currTemp)
                {
                    coldestDay = f.toString();
                }
            }
        }
        return coldestDay;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord lowestHumidity = null;
        for (CSVRecord currRow : parser)
        {
            if(!currRow.get("Humidity").equals("N/A"))
            {

                if (lowestHumidity == null)
                {
                    lowestHumidity = currRow;
                }
                else{
                    double currentHumd = Double.parseDouble(currRow.get("Humidity"));
                    double lowesthumd = Double.parseDouble(lowestHumidity.get("Humidity"));
                    if(currentHumd < lowesthumd)
                    {
                        lowestHumidity = currRow;
                    }
                }
            }
        }
        return lowestHumidity;
    }
    
    public CSVRecord lowestHumidityInManyFiles()
    {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidity = null;
        
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord currRow = lowestHumidityInFile(fr.getCSVParser());

                if (currRow.get("Humidity") != "N/A") 
                {

                    if(lowestHumidity == null)
                    {
                        lowestHumidity = currRow;
                    }
                    else{
                         double currentHumd = Double.parseDouble(currRow.get("Humidity"));
                         double lowestHumd = Double.parseDouble(lowestHumidity.get("Humidity")); 
                         if (currentHumd < lowestHumd)
                         {
                             lowestHumidity = currRow;
                         }
                    }
                }
            
        }return lowestHumidity;
    }
    
    public double averageTemperatureInFile(CSVParser parser)
    {
        double sumTemperature = 0.0;
        double numDays = 0.0;
       
        for(CSVRecord currRow : parser)
        {
            double CurrTemp = Double.parseDouble(currRow.get("TemperatureF"));
            sumTemperature = sumTemperature + CurrTemp;
            numDays++;
        }
        double avgTemp = sumTemperature/numDays;
        return avgTemp;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
    {
        double sumTemperature = 0.0;
        double numDays = 0.0;
       
        for(CSVRecord currRow : parser)
        {
            int currHumd = Integer.parseInt(currRow.get("Humidity"));
            if (currHumd >= value)
            {
                double CurrTemp = Double.parseDouble(currRow.get("TemperatureF"));
                sumTemperature = sumTemperature + CurrTemp;
                numDays++;
            }
        }
        double avgTemp = sumTemperature/numDays;
        return avgTemp;
    }     
        
    
    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Coldest temperature is " + coldest.get("TemperatureF") + "on date " + coldest.get("DateUTC"));
    }
    
    public void testfileWithColdestTemperature()
    {
        String coldestDay = fileWithColdestTemperature();
        System.out.println("File With coldest temperature is " + coldestDay);
        FileResource fr = new FileResource(coldestDay);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        for (CSVRecord currRecord : fr.getCSVParser())
        {
            System.out.println(currRecord.get("DateUTC") + " : " + currRecord.get("TemperatureF"));
        }
    }
    
    public void testlowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumid = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + lowestHumid.get("Humidity") + " at " + 
                                lowestHumid.get("DateUTC"));
    }
    
    public void testlowestHumidityInManyFiles()
    {
        CSVRecord lowestHumd = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + lowestHumd.get("Humidity") + " at " +
                                        lowestHumd.get("DateUTC"));
    }
    
    public void testaverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double AvgTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + AvgTemp);
    }
    
    public void testaverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTempWithHighHumidity = averageTemperatureWithHighHumidityInFile(parser, 80);
        System.out.println("Average temperature with high humidity is " + avgTempWithHighHumidity);
    }
}



