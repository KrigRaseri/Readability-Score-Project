package readability_score;

public class AgeGroupEnum {

    enum AgeGroup {
        ONE(1, "5-6", "Kindergarten"), TWO(2, "6-7", "First Grade"),
        THREE(3, "7-8", "Second Grade"), FOUR(4, "8-9", "Third Grade"),
        FIVE(5, "9-10", "Fourth Grade"), SIX(6, "10-11", "Fifth Grade"),
        SEVEN(7, "11-12", "Sixth Grade"), EIGHT(8, "12-13", "Seventh Grade"),
        NINE(9, "13-14", "Eighth Grade"), TEN(10, "14-15", "Ninth Grade"),
        ELEVEN(11, "15-16", "Tenth Grade"), TWELVE(12, "16-17", "Eleventh Grade"),
        THIRTEEN(13, "17-18", "Twelfth Grade"), FOURTEEN(14, "18-22", "College");

        private int score;
        private String ageGroup;
        private String grade;

        AgeGroup(int score, String ageGroup, String grade) {
            this.score = score;
            this.ageGroup = ageGroup;
            this.grade = grade;
        }

        //Methods
        public static String findAgeGroup (double apiScore) {
            int score = (int) Math.ceil(apiScore);

            for (AgeGroup value : values()) {
                if (value.score == score) {
                    return value.ageGroup;
                }
            }
            return null;
        }


        //Getters and setters
        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getAgeGroup() {
            return ageGroup;
        }

        public void setAgeGroup(String ageGroup) {
            this.ageGroup = ageGroup;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
    }
}
