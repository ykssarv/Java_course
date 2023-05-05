package ee.taltech.iti0202.university.declaration.strategy;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.entity.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EasyCoursesStrategy extends BasicDeclarationStrategy {
    /**
     * Instantiates a new Easy courses strategy.
     *
     * @param student the student
     */
    public EasyCoursesStrategy(Student student) {
        super(student);
    }

    @Override
    public List<Course> findPreferredCourses(int minimumCreditPoints) {
        List<Course> passedCourses = student.getPassedCourses();
        List<Course> preferredCourses = student.getStudyProgramme().getCourses().stream()
            .filter(x -> !passedCourses.contains(x))
            .sorted(Comparator.comparing(Course::getCreditPoints))
            .collect(Collectors.toList());

        List<Course> chosen = new ArrayList<>();
        int totalPoints = 0;
        for (Course course : preferredCourses) {
            chosen.add(course);
            totalPoints += course.getCreditPoints();
            if (totalPoints >= minimumCreditPoints) {
                return chosen;
            }
        }
        return chosen;
    }
}
