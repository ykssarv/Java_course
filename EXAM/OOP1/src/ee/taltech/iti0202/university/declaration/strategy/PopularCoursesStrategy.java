package ee.taltech.iti0202.university.declaration.strategy;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.entity.Student;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PopularCoursesStrategy extends BasicDeclarationStrategy {
    /**
     * Instantiates a new Popular courses strategy.
     *
     * @param student the student
     */
    public PopularCoursesStrategy(Student student) {
        super(student);
    }

    @Override
    public List<Course> findPreferredCourses(int minimumCreditPoints) {
        List<Course> passedCourses = student.getPassedCourses();
        return student.getStudyProgramme().getCourses().stream()
            .filter(x -> !passedCourses.contains(x))
            .sorted(Collections.reverseOrder(Comparator.comparing(Course::getDeclarationAmount)))
            .collect(Collectors.toList());
    }
}
