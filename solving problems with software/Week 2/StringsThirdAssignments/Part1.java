
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
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

    public StorageResource getAllGenes(String dna)
    {
        StorageResource geneList = new StorageResource ();
        int startIndex = 0;

        while(true)
        {
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty())
            {
                break;
            }
            geneList.add(currentGene);

            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();

        }
        return geneList;

    }

    public int countGenes(String dna)
    {
        int startIndex = 0;
        int count = 0;
        while(true)
        {
            String currGene = findGene(dna, startIndex);
            if (currGene.isEmpty())
            {
                break;
            }
            count++;

            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return count;
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

    public void testCountGenes()
    {
        String dna = "ATGTAAGATGCCCTAGT";
        int numGenes = countGenes(dna);
        System.out.println("Number of genes in " + dna +" = " + numGenes);
    }
    
    public void testOngetAllGenes(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        StorageResource store = getAllGenes(dna);
        for (String g : store.data())
        {
            System.out.println(g);
        }
    }
    
    public void testgetAllGenes() {
        testOngetAllGenes("ATGxxxTAAxxxATGxxxyyyzzzTGAxxx");
        testOngetAllGenes("");
        testOngetAllGenes("ATGxxxyyyzzzTAGxxxyyyATGxxTAAxyyyzzzATGTAA");
    }

   
}
