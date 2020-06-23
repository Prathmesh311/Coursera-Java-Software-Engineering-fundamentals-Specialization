
/**
 * Write a description of Part1 here.
 * 
 * @author Prathmesh Bhondave 
 * @version 05/30/2020
 */

public class Part1 {
    public String findSimpleGene(String dna)
    {
        String Result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1)
        {
            return Result;
        }
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        if (stopIndex == -1)
        {
            return Result;
        }
        Result = dna.substring(startIndex, stopIndex + 3);
        return Result;
    }

    public void testSimpleGene1 () {
        String dna = "ACTATGACTGTACGATAAGGC";
        System.out.println("\nDNA Strade is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);

        dna = "ATGAAAGCCTAGC";
        System.out.println("\nDNA Strade is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);

        dna = "AAAGCCTAGCACTAA";
        System.out.println("\nDNA Strade is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);

        dna = "ATGAAAGCCTAGCTAAAAAA";
        System.out.println("\nDNA Strade is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);


    }

        

}
