package ee.taltech.iti0202.university.declaration;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.entity.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class StudyPlan {
    private Student student;
    private HashMap<Integer, Declaration> declarations;
    private Integer id;
    private static int count = 0;
    private boolean active;

    /**
     * Instantiates a new Study plan.
     *
     * @param student the student
     */
    public StudyPlan(Student student) {
        this.declarations = new HashMap<>();
        this.student = student;
        count += 1;
        this.id = count;
        this.active = false;

    }

    /**
     * Add new declaration to study plan.
     *
     * @param course to make declaration from.
     */
    public void addDeclaration(Course course) {
        Declaration declaration = new Declaration(course, this);
        declarations.put(declaration.getId(), declaration);
    }

    public boolean isActive() {
        return active;
    }

    /**
     * Activate study plan.
     */
    public void activate() {
        active = true;
    }

    /**
     * Deactivate study plan.
     */
    public void deactivate() {
        active = false;
    }

    /**
     * Gets declaration by course.
     *
     * @param course the course
     * @return optional of declaration
     */
    public Optional<Declaration> getDeclarationByCourse(Course course) {
        return declarations.values().stream().filter(x -> x.getCourse() == course).findFirst();
    }

    /**
     * Check if study plan has active declarations.
     *
     * @return the boolean
     */
    public boolean hasActiveDeclarations() {
        return declarations.values().stream().anyMatch(x -> !x.isFinished());
    }

    public int getTotalCreditPoints() {
        return declarations.values()
            .stream()
            .filter(Declaration::isFinished)
            .filter(Declaration::isPassed)
            .mapToInt(x -> x.getCourse().getCreditPoints())
            .sum();
    }

    public List<Declaration> getDeclarations() {
        return new ArrayList<>(declarations.values());
    }
}
