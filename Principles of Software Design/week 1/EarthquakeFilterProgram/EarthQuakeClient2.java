import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        Filter f = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        //prints all quakes
        /*for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        */
       
        //Test 1
        /*Filter f1 = new MagnitudeFilter(4.0, 5.0);
        Filter f2 = new DepthFilter(-35000, -12000);
        for(QuakeEntry qe : list)
        {
            if(f1.satisfies(qe))
            {
                if(f2.satisfies(qe))
                {
                    System.out.println(qe);
                }
            }
        }*/
        
       //Test 2
       /*Location Colorado  = new Location(39.7392, -104.9903);
       Filter f1 = new DistanceFilter(Colorado , 1000000);
       Filter f2 = new PhraseFilter("end", "a");*/
       Filter f1 = new DepthFilter(-55000, -20000);
       Filter f2 = new MagnitudeFilter(3.5,4.5);
       int count = 0;
       
       for(QuakeEntry qe : list)
       {
           if(f1.satisfies(qe))
           {
               if(f2.satisfies(qe))
               {
                   System.out.println(qe);
                   count++;
                }
            }
        }
       System.out.println("total count = " + count);
    }
    
    public void testMatchAllFilter()
    {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        //by using MatchAllFilter class
        MatchAllFilter maf = new MatchAllFilter();
        
        //adding filter in MatchAllFilters
        Filter f1 = new MagnitudeFilter(1.0, 4.0);
        maf.addFilter(f1);
        Filter f2 = new DepthFilter(-180000, -30000);
        maf.addFilter(f2);
        Filter f3 = new PhraseFilter("any", "o");
        maf.addFilter(f3);
        
        //evaluating MatchAllFilter
        ArrayList<QuakeEntry> mafList = filter(list, maf);
        for(QuakeEntry qe : mafList)
        {
            System.out.println(qe);
        }
        System.out.println("Found " + mafList.size() + " quakes that match that criteria.");
        
        System.out.println(maf.getName());
    }
        
    public void testMatchAllFilter2()
    {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        //by using MatchAllFilter class
        MatchAllFilter maf = new MatchAllFilter();
        
        //adding filters in MatchAllFilters
        Filter f1 = new MagnitudeFilter(0.0, 5.0);
        maf.addFilter(f1);
        
        //creting Location variable of Oklahoma 
        Location Denmark  = new Location(55.7308, 9.1153);
        Filter f2 = new DistanceFilter(Denmark , 3000000);
        maf.addFilter(f2);
        
        Filter f3 = new PhraseFilter("any", "e");
        maf.addFilter(f3);
        
        //evaluating MatchAllFilter
        ArrayList<QuakeEntry> mafList = filter(list, maf);
        for(QuakeEntry qe : mafList)
        {
            System.out.println(qe);
        }
        System.out.println("Found " + mafList.size() + " quakes that match that criteria.");
        
    }
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    
}
