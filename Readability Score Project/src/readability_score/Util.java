package readability_score;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Utility class for running, showing menu, printing out results, etc.
 *
 * @author Krig Raseri (pen name)
 * */
public class Util {

    /**
     * Runs the program by calling all relevant methods.
     *
     * @param reader Represents the file being read by the buffered reader.
     * */
    public static void run(BufferedReader reader) {
        try {
            String input = reader.readLine();
            String[] sentences = input.split("[!.?]+");
            int nWords = input.split(" ").length;
            int nSentences = sentences.length;
            int nChars = input.replaceAll("\\s", "").length();
            int nSyllables = Util.countSyllables(input);
            int nPolysyllables = Util.countPolySyllables(input);

            Util.printStats(nWords, nSentences, nChars, nSyllables, nPolysyllables);
            menu(nWords, nSentences, nChars, nSyllables, nPolysyllables);

            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    /**
     * Menu method that takes user input to choose and run various options.
     *
     * @param (nWords, nSentences, nChars, nSyllables, nPolySyllables) Represents the total count of each.
     * */
    private static void menu (int nWords, int nSentences, int nChars, int nSyllables, int nPolySyllables) {
        ReadabilityAlgorithms a = new ReadabilityAlgorithms();
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        try (BufferedReader inp = new BufferedReader(new InputStreamReader(System.in))) {
            switch (inp.readLine().toUpperCase()) {
                case "ARI" -> {
                    double ariScore = a.calculateARI(nWords, nSentences, nChars);
                    System.out.printf("Automated Readability Index: %.2f (about %s-year-olds).\n",
                            ariScore, AgeGroupEnum.AgeGroup.findAgeGroup(ariScore));
                }
                case "FK" -> {
                    double fkScore = a.calculateFK(nWords, nSentences, nSyllables);
                    System.out.printf("Flesch–Kincaid readability tests: %.2f (about %s-year-olds).",
                            fkScore, AgeGroupEnum.AgeGroup.findAgeGroup(fkScore));
                }
                case "SMOG" -> {
                    double smogScore = a.calculateSMOG(nPolySyllables, nSentences);
                    System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s-year-olds).",
                            smogScore, AgeGroupEnum.AgeGroup.findAgeGroup(smogScore));
                }
                case "CL" -> {
                    double clScore = a.calculateCL(nSentences, nWords, nChars);
                    System.out.printf("Coleman–Liau index: %.2f (about %s-year-olds).",
                            clScore, AgeGroupEnum.AgeGroup.findAgeGroup(clScore));
                }
                case "ALL" -> printAllAlgorithms(a.calculateARI(nWords, nSentences, nChars),
                        a.calculateFK(nWords, nSentences, nSyllables),
                        a.calculateSMOG(nPolySyllables, nSentences),
                        a.calculateCL(nSentences, nWords, nChars));
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
    }


    /**
     * Grabs the age from AgeGroup that corresponds to the score, then displays all calculated readability scores,
     * as well as the average of all ages.
     * */
    private static void printAllAlgorithms(double ariScore, double fkScore, double smogScore, double clScore) {
        //Stores the age based off of the readability score as int variables.
        int ariAge = Integer.parseInt(Objects.requireNonNull(AgeGroupEnum.AgeGroup.findAgeGroup(ariScore)));
        int fkAge = Integer.parseInt(Objects.requireNonNull(AgeGroupEnum.AgeGroup.findAgeGroup(fkScore)));
        int smogAge = Integer.parseInt(Objects.requireNonNull(AgeGroupEnum.AgeGroup.findAgeGroup(smogScore)));
        int clAge = Integer.parseInt(Objects.requireNonNull(AgeGroupEnum.AgeGroup.findAgeGroup(clScore)));

        System.out.printf("Automated Readability Index: %.2f (about %d-year-olds).\n", ariScore, ariAge);
        System.out.printf("Flesch–Kincaid readability tests: %.2f (about %s-year-olds).\n", fkScore, fkAge);
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s-year-olds).\n", smogScore, smogAge);
        System.out.printf("Coleman–Liau index: %.2f (about %s-year-olds).\n", clScore, clAge);

        System.out.printf("This text should be understood in average by %.2f-year-olds\n",
                ((ariAge + fkAge + smogAge + clAge) / 4.0));
    }

    /**
     * Counts the number of syllables in the input string.
     *
     * @param input Represents where the contents of the file is now stored as a string.
     * */
    private static int countSyllables(String input) {
        int sylCount = 0;
        String[] inp = input.split(" ");
        for (String s : inp) {
            if (s.length() <= 3) {
                sylCount++;
                continue;
            }

            String word = s.replaceAll("e$", "n")
                    .replaceAll("[aeiouy]{2}", "a")
                    .replaceAll("[aeiouy]{2}", "a")
                    .replaceAll("[^aeiouy]", "");

            sylCount += word.length();
        }
        return sylCount;
    }

    /**
     * Counts the number of polysyllables(more than 3 syllables) in the input string.
     *
     * @param input Represents where the contents of the file is now stored as a string.
     * */
    private static int countPolySyllables(String input) {
        int polySylCount = 0;
        String[] inp = input.split(" ");
        for (String s : inp) {
            if (s.length() <= 3) {
                continue;
            }

            String word = s.replaceAll("e$", "n")
                    .replaceAll("[aeiouy]{2}", "a")
                    .replaceAll("[aeiouy]{2}", "a")
                    .replaceAll("[^aeiouy]", "");

            if (word.length() >= 3) {
                polySylCount++;
            }
        }
        return polySylCount;
    }

    //Prints each total count of words, sentences, characters, syllables, and polysyllables.
    private static void printStats (int nWords, int nSentences, int nChars, int nSyllables, int nPolySyllables) {
        System.out.printf("Words: %d\n" +
                        "Sentences: %d\n" +
                        "Characters: %d\n" +
                        "Syllables: %d\n" +
                        "Polysyllables: %d\n",
                nWords, nSentences, nChars, nSyllables, nPolySyllables);
    }
}
