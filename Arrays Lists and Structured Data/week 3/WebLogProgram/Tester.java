
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIps = la.countUniqueIPs();
        System.out.println("Totla unique Ip addresses = " + uniqueIps);
    }
    
    public void testprintAllHigherThanNum()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
        
    
    public void testuniqueIPVisitsOnDay()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> uniqueIpsToday = la.uniqueIPVisitsOnDay("Sep 27");
        for(String s : uniqueIpsToday)
        {
            System.out.println("Unique Ips in date = " + s + "\t");
        }
        System.out.println(uniqueIpsToday.size());
    }
    
    
    public void testcountUniqueIPsInRange()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int IpsInRange = la.countUniqueIPsInRange(400,499);
        System.out.println("Unique Ips in range = " + IpsInRange);
    }
    
    public void testcountVisitsPerIP()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> visitsPerIps = la.countVisitsPerIP();
        System.out.println(visitsPerIps);
    }
    
    public void testmostNumberVisitsByIP()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int maxVisits = la.mostNumberVisitsByIP(la.countVisitsPerIP());
        System.out.println("Most number of visits by any Ip = " + maxVisits);
    }
        
    public void testiPsMostVisits()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> maxVisited = la.iPsMostVisits(la.countVisitsPerIP());
        System.out.println(maxVisited);
    }
    
    public void testiPsForDays()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ipsForDay = la.iPsForDays();
        System.out.println(ipsForDay.get("Sep 21").size());
    }
    
    public void testdayWithMostIPVisits()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        String mostVisitedDay = la.dayWithMostIPVisits(la.iPsForDays());
        System.out.println("Most visited day = " + mostVisitedDay);
    }
    
    public void testiPsWithMostVisitsOnDay()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        ArrayList<String> mostVisitedDays = la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 30");
        System.out.println(mostVisitedDays);
    }
}
