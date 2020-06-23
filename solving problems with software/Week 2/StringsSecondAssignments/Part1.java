
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findGene(String dna, int where)
    {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1)
        {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length())
        {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);

    }

    public int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);

        while(stopIndex != -1)
        {
            if((startIndex - stopIndex) % 3 == 0)
            {
                return stopIndex;
            }
            else{
                stopIndex = dna.indexOf(stopCodon, stopIndex + 3);
            }
        }
        return dna.length();
    }

    public void printAllGenes(String dna)
    {
        int startIndex = 0;

        while(true)
        {
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty())
            {
                break;
            }
            System.out.println("gene is " +currentGene );

            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();

        }

    }

    public void testFindStopCodon()
    {
        String dna = "CGGTATGACCAGTAAAC";
        int startIndex = dna.indexOf("ATG");
        int stopIndex = findStopCodon(dna, startIndex,"TAA");
        System.out.println("Stop Index = " + stopIndex);

        dna = "ATGCCCGATTAGAACT";
        startIndex = dna.indexOf("ATG");
        stopIndex = findStopCodon(dna, startIndex,"TAG");
        System.out.println("Stop Index = " + stopIndex);

        dna = "ATGCCCGATTAGTGA";
        startIndex = dna.indexOf("ATG");
        stopIndex = findStopCodon(dna, startIndex,"TGA");
        System.out.println("Stop Index = " + stopIndex);

        dna = "ATGCCCGATTAGCA";
        startIndex = dna.indexOf("ATG");
        stopIndex = findStopCodon(dna, startIndex,"TGA");
        System.out.println("Stop Index = " + stopIndex);

    }

    public  void testFindGene()
    {
        String dna = "AAATGGCTACGTAGAC";
        String gene = findGene(dna,0);
        System.out.println("DNA is " + gene);

        dna = "AGCTATGATGGCTA";
        gene = findGene(dna,0);
        System.out.println("DNA is " + gene);

        dna = "ATCGTACGATCGATAATAG";
        gene = findGene(dna,0);
        System.out.println("DNA is " + gene);

        dna = "TAGATGCGTTAGTAA";
        gene = findGene(dna,0);
        System.out.println("DNA is " + gene);

        dna = "AGCTATGACTTACCAGTGAAA";
        gene = findGene(dna,0);
        System.out.println("DNA is " + gene);
    }
    
    // test for printAllGenes
    public void testOn(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
    }
    
    public void test() {
        testOn("ATGxxxTAAxxxATGxxxyyyzzzTGAxxx");
        testOn("");
        testOn("ATGxxxyyyzzzTAGxxxyyyATGxxTAAxyyyzzzATGTAA");
    }
}
