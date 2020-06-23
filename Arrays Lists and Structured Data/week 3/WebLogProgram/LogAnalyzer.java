
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines())
         {
             LogEntry currInfo = WebLogParser.parseEntry(line);
             records.add(currInfo);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs()
     {
         ArrayList<String> uniqueIps = new ArrayList<String>();
         
         for(LogEntry currRecord : records)
         {
             String currIp = currRecord.getIpAddress();
             if(!uniqueIps.contains(currIp))
             {
                 uniqueIps.add(currIp);
             }
         }
         return uniqueIps.size();
     }
               
     public void printAllHigherThanNum(int num)
    {
        for(LogEntry le : records)
        {
            int statusCode = le.getStatusCode();
            if(statusCode > num)
            {
                System.out.println(le);
            }
        }
    }
     
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday)
    {
        ArrayList<String> todayIps = new ArrayList<String>();
        
        for(LogEntry le : records)
        {
            String currDate = le.getAccessTime().toString();
            String currIp = le.getIpAddress();
            if(currDate.contains(someday) && !todayIps.contains(currIp))
            {
                todayIps.add(currIp);
            }
        }
        return todayIps;
    }
    
    public int countUniqueIPsInRange(int low, int high)
    {
        ArrayList<String> uniqueIpsInRange = new ArrayList<String>();
        for(LogEntry le : records)
        {
            int statusCode = le.getStatusCode();
            String Ips = le.getIpAddress();
            if(low <= statusCode && statusCode >= high && !uniqueIpsInRange.contains(Ips))
            {
                uniqueIpsInRange.add(Ips);
            }
        }
        return uniqueIpsInRange.size();
    }
            
                
    public HashMap<String, Integer> countVisitsPerIP()
    {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        
        for(LogEntry le : records)
        {
            String Ips = le.getIpAddress();
            if(!counts.containsKey(Ips))
            {
                counts.put(Ips, 1);
            }
            else{
                counts.put(Ips, counts.get(Ips) + 1);
            }
        }
        return counts;
    }
        
        
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts)
    {
        int maxVisits = 0;
        
        for(LogEntry le : records)
        {
            String Ip = le.getIpAddress();
            int currVisit = counts.get(Ip);
            if(currVisit > maxVisits)
            {
                maxVisits = currVisit;
            }
        }
        return maxVisits;
    }
        
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts)
    {
        ArrayList<String> IpsMostVisited = new ArrayList<String>();
        //int maxVisit = 0;
        
        int maxVisit = mostNumberVisitsByIP(counts);
        for(LogEntry le : records)
        {
            String currIp = le.getIpAddress();
            int currvisits = counts.get(currIp);
            if(currvisits == maxVisit && !IpsMostVisited.contains(currIp))
            {
                IpsMostVisited.add(currIp);
            }
        }
        return IpsMostVisited;
    }
        
    public HashMap<String, ArrayList<String>> iPsForDays()
    {
        HashMap<String, ArrayList<String>> ipsForDay = new HashMap<String, ArrayList<String>>();
        //ArrayList<String> uniqueDates= new ArrayList<String>();
        
        for(LogEntry le : records)
        {
            String currDate = le.getAccessTime().toString().substring(4,10);
            String Ips = le.getIpAddress();
            if(!ipsForDay.containsKey(currDate))
            {
                ArrayList<String> ipsVisited = new ArrayList<String>();
                ipsVisited.add(Ips);
                ipsForDay.put(currDate, ipsVisited);
            }
            else
            {
                ipsForDay.get(currDate).add(Ips);
                ipsForDay.put(currDate,  ipsForDay.get(currDate));
            }
        }
        return ipsForDay;
    }
            
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipForDay)
    {
        
        String dayWithmostVisits = "";
        int maxVisit = 0;
        
        for(String date : ipForDay.keySet())
        {
            int currVisits = ipForDay.get(date).size();
            if(currVisits > maxVisit)
            {
                dayWithmostVisits = date;
                maxVisit = currVisits;
            }
        }
        return dayWithmostVisits;
    }
        
        
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipForDay, String day)
    {
        //ArrayList<String> allIps = new ArrayList<String>();
        HashMap<String, Integer> Ips = new HashMap<String, Integer>();
        
        ArrayList<String> allIps = ipForDay.get(day);
        
        for(String s : allIps)
        {
            if(!Ips.containsKey(s))
            {
                Ips.put(s, 1);
            }
            else
            {
                Ips.put(s, Ips.get(s) + 1);
            }
        }
        return iPsMostVisits(Ips);
    }
        
}
