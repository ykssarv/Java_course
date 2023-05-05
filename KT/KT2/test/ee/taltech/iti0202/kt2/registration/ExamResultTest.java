package ee.taltech.iti0202.kt2.registration;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;

public class ExamResultTest {

    public static final int DURATION = 120;
    public static final int MAX_SCORE = 100;
    public static final int WORST_SCORE_THAT_PASSES = 51;
    public static final int BEST_SCORE_THAT_FAILS = 50;
    public static final int SAMPLE_SCORE = 85;
    private ExamTime examTime;

    /**
     * Simpler constructor and getter test.
     */
    @Test
    public void testExamResultCreation() {
        examTime = new ExamTime(LocalDateTime.now(), DURATION, "Urmas");
        ExamResult examResult = new ExamResult(SAMPLE_SCORE, examTime, "Helena");
        Assertions.assertEquals(SAMPLE_SCORE, examResult.getScore());
        Assertions.assertEquals(examTime, examResult.getExamTime());
        Assertions.assertEquals("Helena", examResult.getStudent());
    }

    /**
     * Check that a result with a score of 50 counts as failed.
     */
    @Test
    public void testExamResult50IsFailed() {
        examTime = new ExamTime(LocalDateTime.now(), DURATION, "Urmas");
        ExamResult examResult = new ExamResult(BEST_SCORE_THAT_FAILS, examTime, "Helena");
        Assertions.assertEquals(0, examResult.getGrade());
    }

    /**
     * Check that a result with a score of 51 counts as passed.
     */
    @Test
    public void testExamResult51IsPassed() {
        examTime = new ExamTime(LocalDateTime.now(), DURATION, "Urmas");
        ExamResult examResult = new ExamResult(WORST_SCORE_THAT_PASSES, examTime, "Helena");
        Assertions.assertEquals(1, examResult.getGrade());
    }

    /**
     * Check that a result with a score of 100 is marked as 5.
     */
    @Test
    public void testExamResult100Is5() {
        examTime = new ExamTime(LocalDateTime.now(), DURATION, "Urmas");
        ExamResult examResult = new ExamResult(MAX_SCORE, examTime, "Helena");
        Assertions.assertEquals(5, examResult.getGrade());
    }
}
