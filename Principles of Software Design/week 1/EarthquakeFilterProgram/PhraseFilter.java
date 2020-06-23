
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    
    public PhraseFilter(String wh , String ph)
    {
        where = wh;
        phrase = ph;
    }
    
    public boolean satisfies (QuakeEntry qe)
    {
        return ((where.equals("start") && qe.getInfo().startsWith(phrase)) ||
                 (where.equals("any")   && qe.getInfo().contains(phrase))   ||
                 (where.equals("end")   && qe.getInfo().endsWith(phrase)));
    }
    
    public String getName()
    {
        return "Phrase";
    }
}
