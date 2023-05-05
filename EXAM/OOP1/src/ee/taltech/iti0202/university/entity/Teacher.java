package ee.taltech.iti0202.university.entity;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.declaration.Declaration;
import ee.taltech.iti0202.university.exceptions.IllegalGradeException;
import ee.taltech.iti0202.university.exceptions.NoSuchDeclarationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Teacher {
    private String name;
    private HashMap<String, Course> courses;
    private Integer id;
    private static int count = 0;

    /**
     * Instantiates a new Teacher.
     *
     * @param name the name
     */
    public Teacher(String name) {
        this.name = name;
        this.courses = new HashMap<>();
        count += 1;
        this.id = count;
    }

    /**
     * Add course to teacher
     *
     * @param course to add
     */
    public void addCourse(Course course) {
        courses.put(course.getName(), course);
    }

    /**
     * Grade the given student in course with the given name.
     *
     * @param student    to grade
     * @param courseName of course to grade
     * @param grade      to give
     */
    public void grade(Student student, String courseName, int grade) {
        if (grade < 0 || grade > 5) {
            throw new IllegalGradeException();
        }
        Course course = courses.get(courseName);
        Optional<Declaration> declaration = student.getDeclarationByCourse(course);
        if (declaration.isEmpty()) {
            throw new NoSuchDeclarationException();
        }
        if (course.getGradingType() == Course.GradingType.GRADED) {
            declaration.get().grade(grade);
        } else {
            declaration.get().grade(grade > 0);
        }
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses.values());
    }
}
