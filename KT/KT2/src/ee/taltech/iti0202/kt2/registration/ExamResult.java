package ee.taltech.iti0202.kt2.registration;

public class ExamResult {

    public static final int MIN_SCORE = 51;
    private int grade;
    private int score;
    private String student;
    private ExamTime examTime;

    /**
     * Exam result constructor.
     * @param score of student.
     * @param examTime describing when result was gotten.
     * @param student who got the result.
     */
    public ExamResult(int score, ExamTime examTime, String student) {
        this.examTime = examTime;
        this.score = score;
        this.student = student;
        if (score < MIN_SCORE) {
            this.grade = 0;
        } else {
            this.grade = (score - MIN_SCORE) / 10 + 1;
        }
    }

    public int getGrade() {
        return grade;
    }

    public int getScore() {
        return score;
    }

    public String getStudent() {
        return student;
    }

    public ExamTime getExamTime() {
        return examTime;
    }
}
