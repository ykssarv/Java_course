package ee.taltech.iti0202.university.declaration.strategy;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.Module;
import ee.taltech.iti0202.university.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TakeFromEachModuleStrategy extends BasicDeclarationStrategy {
    /**
     * Instantiates a new Take from each module strategy.
     *
     * @param student the student
     */
    public TakeFromEachModuleStrategy(Student student) {
        super(student);
    }

    @Override
    public List<Course> findPreferredCourses(int minimumCreditPoints) {
        List<Course> chosen = new ArrayList<>();
        List<Course> passedCourses = student.getPassedCourses();
        for (Module module : student.getStudyProgramme().getModules()) {
            Optional<Course> course = module.getCourses().stream()
                .filter(x -> !passedCourses.contains(x))
                .findAny();
            course.ifPresent(chosen::add);
        }
        return chosen;
    }
}
