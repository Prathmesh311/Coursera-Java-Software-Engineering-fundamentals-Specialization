
/**
 * Write a description of CountryExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class CountryExport {
    public void countryInfo(CSVParser parser, String countryName)
    {
        for (CSVRecord record : parser)
        {
            String Country = record.get("Country");
            if (Country.equals(countryName))
            {
                String Exports = record.get("Exports");
                String Value = record.get("Value (dollars)");
                System.out.print(Country + " : ");
                System.out.print(Exports + " : ");
                System.out.print(Value + "\n");
            }
        }
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2)
    {
        for (CSVRecord record : parser)
        {
            String country = record.get("Country");
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2))
            {
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem)
    {
        int count = 0;
        
        for (CSVRecord record : parser)
        {
            String exports = record.get("Exports");
            if (exports.contains(exportItem))
            {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount)
    {
        int amountLen = amount.length();
        
        for (CSVRecord record : parser)
        {
            String country = record.get("Country");
            String Value = record.get("Value (dollars)");
            int valueLen = Value.length();
            if (amountLen < valueLen)
            {
                System.out.print(country + " ");
                System.out.print(Value + "\n");
            }
        }
    }
    
 
    public void test()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //countryInfo(parser, "Nauru");
        bigExporters(parser, "$999,999,999,999");
        //int numCountries = numberOfExporters(parser, "cocoa");
        //System.out.println("Number of countries export gold = " + numCountries);
        //listExportersTwoProducts(parser, "cotton", "flowers");
        
    }

}
