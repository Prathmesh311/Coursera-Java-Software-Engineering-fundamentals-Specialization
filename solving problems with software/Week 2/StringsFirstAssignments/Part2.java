
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String Result = "";
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return Result;
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1) {
            return Result;
        }
        Result = dna.substring(startIndex, stopIndex + 3);
        if (Result.length() % 3 != 0)
        {
            return "";
        }
        return Result;
    }

    public void testSimpleGene2() {
        String startCodon = "ATG";
        String stopCodon = "TAA";
        String dna = "ACTATGACTGTACGATAAGGC";
        System.out.println("\nDNA Strade is " + dna);
        String gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);

        dna = "ATGAAAGCCTAGC";
        System.out.println("\nDNA Strade is " + dna);
        gene = findSimpleGene(dna,startCodon, stopCodon);
        System.out.println("Gene is " + gene);

        dna = "AAAGCCTAGCACTAA";
        System.out.println("\nDNA Strade is " + dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);

        dna = "ATGAAAGCCTAGCTAAAAAA";
        System.out.println("\nDNA Strade is " + dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);

    }
}

