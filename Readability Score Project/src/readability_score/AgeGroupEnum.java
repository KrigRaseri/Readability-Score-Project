package readability_score;

/**
 * Enum class, holds the value based on the readability scores, and the corresponding age group to said score.
 *
 * @author Krig Raseri (pen name)
 * */
public class AgeGroupEnum {

    enum AgeGroup {
        ONE(1, "6"), TWO(2, "7"),
        THREE(3, "8"), FOUR(4, "9"),
        FIVE(5, "10"), SIX(6, "11"),
        SEVEN(7, "12"), EIGHT(8, "13"),
        NINE(9, "14"), TEN(10, "15"),
        ELEVEN(11, "16"), TWELVE(12, "17"),
        THIRTEEN(13, "18"), FOURTEEN(14, "22");

        private final int score;
        private final String ageGroup;

        AgeGroup(int score, String ageGroup) {
            this.score = score;
            this.ageGroup = ageGroup;
        }

        /**
         * Finds the corresponding age group based on readability scores.
         *
         * @param algorithmScore Represents the score from whichever readability algorithm used.
         * */
        public static String findAgeGroup(double algorithmScore) {
            int score = (int) Math.round(algorithmScore);

            for (AgeGroup value : values()) {
                if (value.score == score) {
                    return value.ageGroup;
                }
            }
            return null;
        }
    }
}
