
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
 
public class Part4 {
    public void YouTubeLink(String url)
    {
        URLResource Url = new URLResource(url);
        String youTube = "youtube.com";
        String youTubeLink = "";
        int beginQuote;
        int endQuote;
        int numYouTubeLinks=0;
        
        for (String singleUrl : Url.words())
        {
            String lowUrl = singleUrl.toLowerCase();
            int youTubeOccurrence = lowUrl.indexOf(youTube);
            if (youTubeOccurrence != -1)
            {
                beginQuote = lowUrl.lastIndexOf("\"", youTubeOccurrence);
                endQuote = lowUrl.indexOf("\"", beginQuote + 1);
                youTubeLink = singleUrl.substring(beginQuote + 1, endQuote);
                System.out.println("Youtube lick is " + youTubeLink);

                numYouTubeLinks++;
            }
        }
        System.out.println("The number of links is " + numYouTubeLinks);

    }
   
}
