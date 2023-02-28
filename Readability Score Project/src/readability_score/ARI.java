package readability_score;

import java.io.BufferedReader;
import java.io.IOException;

public class ARI {

    //Methods
    public void runARI(BufferedReader reader, ARI ari) {
        try {
            String input = reader.readLine();
            String[] sentences = input.split("[!.?]+");
            int totalWords = input.split(" ").length;
            int totalSentences = sentences.length;
            int totalChars = input.replaceAll("\\s", "").length();
            double ariScore = ari.calculateARI(totalWords, totalSentences, totalChars);

            ari.printARI(totalWords, totalSentences, totalChars, ariScore);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double calculateARI(int nWords, int nSentences, int nChars) {
        double div1 = nChars / (double) nWords;
        double div2 = nWords / (double) nSentences;
        return ((4.71 * div1) + (0.5 * div2)) - 21.43;
    }

    private void printARI(int nWords, int nSentences, int nChars, double apiScore) {
        System.out.printf("Words: %d\n" +
                "Sentences: %d\n" +
                "Characters: %d\n" +
                "The score is: %.2f\n" +
                "This text should be understood by %s year-olds.\n",
                nWords, nSentences, nChars, apiScore, AgeGroupEnum.AgeGroup.findAgeGroup(apiScore));
    }
}
