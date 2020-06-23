
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb)
    {
        int startIndex = 0;
        int count = 0;

        while (true)
        {
            int currIndex = stringb.indexOf(stringa, startIndex);
            if (currIndex == -1)
            {
                break;
            }
            count++;
            startIndex = stringb.indexOf(stringa, startIndex) + stringa.length();
        }
        return count;
    }

    public void testHowMany()
    {
        int numOccuerrence = howMany("GAA", "ATGAACGAATTGAATC");
        System.out.println("Number of occurrence = " + numOccuerrence);

        numOccuerrence = howMany("AA", "ATAAAA");
        System.out.println("Number of occurrence = " + numOccuerrence);
    }
}
