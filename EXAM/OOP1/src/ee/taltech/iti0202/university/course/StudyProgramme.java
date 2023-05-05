package ee.taltech.iti0202.university.course;

import ee.taltech.iti0202.university.entity.Student;
import ee.taltech.iti0202.university.University;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class StudyProgramme {
    private University university;
    private HashMap<Integer, Student> students;
    private String name;
    private HashMap<String, Module> modules;

    /**
     * Instantiates a new study programme.
     *
     * @param university the university
     * @param name       the name
     */
    public StudyProgramme(University university, String name) {
        this.students = new HashMap<>();
        this.modules = new HashMap<>();
        this.university = university;
        this.name = name;
    }

    public University getUniversity() {
        return university;
    }

    /**
     * Add course to study programme.
     *
     * @param course the course
     * @param module the module
     */
    public void addCourse(Course course, String module) {
        modules.get(module).addCourse(course);
        university.addCourse(course);
        course.getStudyProgrammes().put(this.name, this);
    }

    /**
     * Create module to study programme.
     *
     * @param name the name
     * @return the module
     */
    public Module createModule(String name) {
        Module module = new Module(name, this);
        modules.put(name, module);
        return module;
    }

    /**
     * Add student to study programme.
     *
     * @param student the student
     */
    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public List<Course> getCourses() {
        return modules.values().stream()
            .map(Module::getCourses)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students.values());
    }

    public int getTotalCreditPoints() {
        return modules.values().stream().mapToInt(Module::getTotalCreditPoints).sum();
    }

    public List<Module> getModules() {
        return new ArrayList<>(modules.values());
    }
}
