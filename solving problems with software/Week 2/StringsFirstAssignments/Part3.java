
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){

        int index = 0;
        int count = 0;

        while((index = stringb.indexOf(stringa, index)) != -1){
            count++;
            index++;
        }
        if (count >= 2)
        {
            return true;
        }
        return false;
    }

    public String lastPart(String stringa, String stringb)
    {
        int firstOccurence = stringb.indexOf(stringa);
        String Result = stringb.substring(firstOccurence + stringa.length());
        return Result;

    }

    public void testing()
    {
        boolean result = twoOccurrences("an","banana");
        System.out.println(result);
        String endpart = lastPart("an", "banana");
        System.out.println(endpart);

        result = twoOccurrences("by", "A story by Abby Long");
        System.out.println(result);
        endpart = lastPart("by", "A story by Abby Long");
        System.out.println(endpart);

        
    }
}
