package ee.taltech.iti0202.kt2.registration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Exam {

    private List<ExamTime> times;

    /**
     * Exam constructor.
     */
    public Exam() {
        times = new ArrayList<>();
    }

    /**
     * Add an exam time to the exam.
     * @param time to add.
     */
    public void addTime(ExamTime time) {
        times.add(time);
    }

    /**
     * Get list of all exam times.
     * @return list of times.
     */
    public List<ExamTime> getTimes() {
        return times;
    }

    /**
     * Get results given by the given teacher.
     * @param teacher to filter by.
     * @return List of results that were given by that teacher.
     */
    public List<ExamResult> getResultsForTeacher(String teacher) {
        return times.stream()
            .filter(x -> x.getTeacher().equals(teacher))
            .map(ExamTime::getResults)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    /**
     * Get failures.
     * @return List of results that were failures.
     */
    public List<ExamResult> getFailures() {
        return times.stream()
            .map(ExamTime::getResults)
            .flatMap(Collection::stream)
            .filter(x -> x.getGrade() == 0)
            .collect(Collectors.toList());
    }


}
