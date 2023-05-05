package ee.taltech.iti0202.kt2.registration;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class ExamTimeTest {

    public static final int DURATION = 120;
    public static final int A_LOT = 10000;
    public static final int MAX_SCORE = 100;

    /**
     * Simple constructor and getter test.
     */
    @Test
    public void testExamTimeCreation() {
        LocalDateTime startTime = LocalDateTime.now();
        ExamTime examTime = new ExamTime(startTime, DURATION, "Urmas");
        Assertions.assertEquals(startTime, examTime.getStartTime());
        Assertions.assertEquals(DURATION, examTime.getDuration());
        Assertions.assertEquals("Urmas", examTime.getTeacher());
    }

    /**
     * Test that the do exam method gives correct name to result object.
     */
    @Test
    public void testDoExamGivesResultWithCorrectTimeAndStudent() {
        LocalDateTime startTime = LocalDateTime.now();
        ExamTime examTime = new ExamTime(startTime, DURATION, "Urmas");
        ExamResult examResult = examTime.doExam("Helena");
        Assertions.assertEquals(examTime, examResult.getExamTime());
        Assertions.assertEquals("Helena", examResult.getStudent());
    }

    /**
     * Check that do exam also adds the result to the list of results.
     */
    @Test
    public void testDoExamAddsResultToResultList() {
        LocalDateTime startTime = LocalDateTime.now();
        ExamTime examTime = new ExamTime(startTime, DURATION, "Urmas");
        ExamResult examResult = examTime.doExam("Helena");
        List<ExamResult> results = examTime.getResults();
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals(examResult, results.get(0));
    }

    /**
     * Check that the teacher gives only valid scores for exam.
     */
    @Test
    public void testDoExamGivesScoreInAllowedRange() {
        LocalDateTime startTime = LocalDateTime.now();
        ExamTime examTime = new ExamTime(startTime, DURATION, "Urmas");
        for (int i = 0; i < A_LOT; i++) {
            ExamResult examResult = examTime.doExam("Helena");
            int score = examResult.getScore();
            if (score < 1 || score > MAX_SCORE) {
                fail();
            }
        }
        Assertions.assertEquals(A_LOT, examTime.getResults().size());
    }
}
