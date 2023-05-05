package ee.taltech.iti0202.kt2.registration;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

public class ExamTest {

    public static final int DURATION = 120;
    public static final int RAAVO_DURATION = 100;
    public static final int KALDA_DURATION = 200;
    public static final int A_LOT = 10000;
    private Exam exam;
    private ExamTime examTime;

    /**
     * Check that the add time method adds the given time to the exam.
     */
    @Test
    public void testAddTimeAddsTime() {
        exam = new Exam();
        examTime = new ExamTime(LocalDateTime.now(), DURATION, "Urmas");
        exam.addTime(examTime);
        List<ExamTime> times = exam.getTimes();
        Assertions.assertEquals(1, times.size());
        Assertions.assertEquals(examTime, times.get(0));
    }

    /**
     * Check that the get failures method only returns failures.
     */
    @Test
    public void testGetFailuresReturnsOnlyFailures() {
        exam = new Exam();
        examTime = new ExamTime(LocalDateTime.now(), DURATION, "Urmas");
        exam.addTime(examTime);
        for (int i = 0; i < A_LOT; i++) {
            examTime.doExam("Helena");
        }
        List<ExamResult> failures = exam.getFailures();

        for (ExamResult failure : failures) {
            if (failure.getGrade() != 0) {
                fail();
            }
        }
    }

    /**
     * Check that the get results by teacher method returns only the results with that teacher.
     */
    @Test
    public void testGetResultsByTeacherReturnsOnlyResultsByThatTeacher() {
        exam = new Exam();
        examTime = new ExamTime(LocalDateTime.now(), DURATION, "Urmas");
        ExamTime raavosExam = new ExamTime(LocalDateTime.now(), RAAVO_DURATION, "Raavo");
        ExamTime kaldasExam = new ExamTime(LocalDateTime.now(), KALDA_DURATION, "Kalda");
        exam.addTime(examTime);
        exam.addTime(raavosExam);
        exam.addTime(kaldasExam);
        for (int i = 0; i < A_LOT; i++) {
            int random = new Random().nextInt();
            ExamTime thisExamTime = random % 3 == 0 ? examTime : (random % 3 == 1 ? raavosExam : kaldasExam);
            thisExamTime.doExam("Helena");
        }
        List<ExamResult> resultsByKalda = exam.getResultsForTeacher("Kalda");
        for (ExamResult result : resultsByKalda) {
            if (!result.getExamTime().getTeacher().equals("Kalda")) {
                fail();
            }
        }
    }
}
