package ee.taltech.iti0202.university.declaration.strategy;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasicDeclarationStrategy implements DeclarationStrategy {

    protected Student student;

    public enum StrategyType {
        EASY,
        DIFFERENT,
        POPULAR
    }

    /**
     * Instantiates a new Basic declaration strategy.
     *
     * @param student the student
     */
    public BasicDeclarationStrategy(Student student) {
        this.student = student;
    }

    /**
     * Find random course from student's university that student hasn't taken.
     *
     * @param soFar courses chosen so far
     * @return optional of course
     */
    public Optional<Course> findRandomCourse(List<Course> soFar) {
        List<Course> passedCourses = student.getPassedCourses();
        return student.getUniversity().getCourses().stream()
            .filter(x -> !passedCourses.contains(x))
            .filter(x -> !soFar.contains(x))
            .findAny();
    }

    @Override
    public List<Course> findPreferredCourses(int minimumCreditPoints) {
        return new ArrayList<>();
    }

    @Override
    public List<Course> findFinalCourses(int minimumCreditPoints, int maximumCreditPoints) {
        List<Course> preferred = this.findPreferredCourses(minimumCreditPoints);

        int currentCreditPoints = 0;
        List<Course> chosen = new ArrayList<>();
        for (Course course : preferred) {
            if (currentCreditPoints + course.getCreditPoints() > maximumCreditPoints) {
                return chosen;
            }
            chosen.add(course);
            currentCreditPoints += course.getCreditPoints();
        }

        while (currentCreditPoints < minimumCreditPoints) {
            Optional<Course> course = findRandomCourse(chosen);
            if (course.isEmpty()) {
                return chosen;
            }
            chosen.add(course.get());
            currentCreditPoints += course.get().getCreditPoints();
        }
        return chosen;
    }
}
