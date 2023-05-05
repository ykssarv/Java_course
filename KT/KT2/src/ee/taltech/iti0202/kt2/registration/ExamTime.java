package ee.taltech.iti0202.kt2.registration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExamTime {

    private LocalDateTime startTime;
    private int duration;  // In minutes
    private String teacher;
    private Random teacherMood;
    private List<ExamResult> results;

    /**
     * Exam time constructor.
     * @param startTime time when exam starts
     * @param duration duration of exam
     * @param teacher that does the examination
     */
    public ExamTime(LocalDateTime startTime, int duration, String teacher) {
        this.startTime = startTime;
        this.duration = duration;
        this.teacher = teacher;
        this.teacherMood = new Random();
        this.results = new ArrayList<>();
    }

    /**
     * Student wants to do the exam.
     * @param student that is doing the exam.
     * @return the result object.
     */
    public ExamResult doExam(String student) {
        ExamResult result = new ExamResult(teacherMood.nextInt(100) + 1, this, student);
        this.results.add(result);
        return result;
    }

    /**
     * Get all of the results.
     * @return list of results.
     */
    public List<ExamResult> getResults() {
        return results;
    }

    /**
     * Get the teacher.
     * @return string with teacher name.
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * Get the start time of the exam.
     * @return datetime object with starting time.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Get the duration of the exam in minutes.
     * @return int showing how many minutes the exam lasts for.
     */
    public int getDuration() {
        return duration;
    }
}
