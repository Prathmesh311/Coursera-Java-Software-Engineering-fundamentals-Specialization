import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData)
        {
            double mag = qe.getMagnitude();
            if(mag > magMin)
            {
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData)
        {
            Location loc = qe.getLocation();
            double Dist = loc.distanceTo(from);
            if(Dist < distMax) 
            {
                answer.add(qe);
            }
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> bigMagQuake = filterByMagnitude(list, 5.0);
        for(QuakeEntry qe : bigMagQuake)
        {
            System.out.println(qe);
        }

        System.out.println("Found " + bigMagQuake.size() + " quakes that match that criteria.");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);
        ArrayList<QuakeEntry> distFiltered = filterByDistanceFrom(list, 1000000, city);
        for(QuakeEntry qe : distFiltered)
        {
            System.out.println(qe.getLocation().distanceTo(city)/1000 + " " + qe.getInfo());
        }
        // This location is Bridgeport, CA
        Location city2 =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> distFilter = filterByDistanceFrom(list, 1000000, city2);
        for(QuakeEntry qe : distFilter)
        {
            System.out.println(qe.getLocation().distanceTo(city2)/1000 + " " + qe.getInfo());
        }
        System.out.println("Found " + distFilter.size() + " quakes that match that criteria.");
        // TODO
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for(QuakeEntry qe : quakeData)
        {
            double depth = qe.getDepth();
            if(minDepth < depth && depth < maxDepth)
            {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //for finding phrase at any position
        if(where == "any")
        {
            for(QuakeEntry qe : quakeData)
            {
                String title = qe.getInfo();
                if(title.contains(phrase))
                {
                    answer.add(qe);
                }
            }
        }
        //for finding phrase at start
        if(where == "start")
        {
            for(QuakeEntry qe : quakeData)
            {
                String title = qe.getInfo();
                if(title.startsWith(phrase))
                {
                    answer.add(qe);
                }
            }
        }
        //for finding phrase at end
        if(where == "end")
        {
            for(QuakeEntry qe : quakeData)
            {
                String title = qe.getInfo();
                if(title.endsWith(phrase))
                {
                    answer.add(qe);
                }
            }
        }
        return answer;
        
    }
    
    
    
    
    
    
    
    
    public void quakesOfDepth()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        //reading source data
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        //filtering Quakes by depth between -10000 to -5000
        ArrayList<QuakeEntry> depthFiltered = filterByDepth(list, -4000, -2000);
        //Printing out the filtered quakes
        System.out.println("find quakes between depth -10000 to -5000");
        for(QuakeEntry qe : depthFiltered)
        {
            System.out.println(qe);
        }
        System.out.println("Found " + depthFiltered.size() + " quakes that match that criteria.");
    }
    
    
    public void quakesByPhrase()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        //reading the source data
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        String where = "any";
        String phrase ="Can";
        ArrayList<QuakeEntry> phraseFiltered = filterByPhrase(list, where, phrase);
        for(QuakeEntry qe : phraseFiltered)
        {
            System.out.println(qe);
        }
        System.out.println("Found " + phraseFiltered.size() + " quakes that match " + phrase + " at" + where);
    }
}
