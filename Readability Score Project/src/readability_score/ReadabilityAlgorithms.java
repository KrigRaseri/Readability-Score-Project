package readability_score;

/**
 * Class for all of the readability score calculation algorithms.
 *
 * @author Krig Raseri
 * */
public class ReadabilityAlgorithms {

    public double calculateARI(int nWords, int nSentences, int nChars) {
        return ((4.71 * ((nChars / (double) nWords)) + ((0.5 * nWords / (double) nSentences)) - 21.43));
    }

    public double calculateFK(int nWords, int nSentences, int nSyllables) {
        return 0.39 * nWords / (double) nSentences + 11.8 * nSyllables / (double) nWords - 15.59;
    }

    public double calculateSMOG(int nPolySyllables, int nSentences) {
        return 1.043 * Math.sqrt(nPolySyllables * (30 / (double) nSentences)) + 3.1291;
    }

    public double calculateCL(int nSentences, int nWords, int nChar) {
        double S = nSentences / (double) nWords * 100;
        double L = nChar / (double) nWords * 100;
        return (0.0588 * L) - (0.296 * S) - 15.8;
    }
}
