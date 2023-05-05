package ee.taltech.iti0202.university.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Module {

    private StudyProgramme studyProgramme;
    private HashMap<String, Course> courses;
    private String name;

    /**
     * Instantiates a new Module.
     *
     * @param name           the name
     * @param studyProgramme the study programme
     */
    public Module(String name, StudyProgramme studyProgramme) {
        this.name = name;
        this.studyProgramme = studyProgramme;
        this.courses = new HashMap<>();
    }

    /**
     * Add course.
     *
     * @param course to add
     */
    public void addCourse(Course course) {
        courses.put(course.getName(), course);
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses.values());
    }

    public int getTotalCreditPoints() {
        return courses.values().stream().mapToInt(Course::getCreditPoints).sum();
    }
}
