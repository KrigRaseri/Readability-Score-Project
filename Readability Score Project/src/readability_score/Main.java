package readability_score;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Program that calculates either the Automated Readability Index score, Flesch–Kincaid readability test score,
 * Simple Measure of Gobbledygook score, Coleman–Liau index score, or all scores at once. Each calculation is based off
 * of either the number of words, characters, sentences, syllables, and polysyllables.
 *
 * @author Krig Raseri (pen name)
 * */
public class Main {
    //args[0] is a string file name input as a CLI argument.
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            Util.run(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}