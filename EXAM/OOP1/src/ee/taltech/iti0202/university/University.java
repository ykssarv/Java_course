package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.StudyProgramme;
import ee.taltech.iti0202.university.entity.Student;
import ee.taltech.iti0202.university.exceptions.MaximumCreditPointsTooSmallException;
import ee.taltech.iti0202.university.exceptions.MinimumCreditPointsNegativeException;
import ee.taltech.iti0202.university.exceptions.ProgrammeExistsException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class University {
    public static final int DEFAULT_MAXIMUM_CREDIT_POINTS = 60;
    public static final int DEFAULT_MINIMUM_CREDIT_POINTS = 0;
    private HashMap<String, StudyProgramme> programmes;
    private HashMap<String, Course> courses;
    private HashMap<Integer, Scholarship> scholarships;
    private String name;
    private int minimumCreditPoints;
    private int maximumCreditPoints;

    /**
     * Instantiates a new University.
     *
     * @param name                the name
     * @param minimumCreditPoints the minimum credit points
     * @param maximumCreditPoints the maximum credit points
     */
    public University(String name, int minimumCreditPoints, int maximumCreditPoints) {
        if (minimumCreditPoints < 0) {
            throw new MinimumCreditPointsNegativeException();
        }
        if (maximumCreditPoints <= minimumCreditPoints) {
            throw new MaximumCreditPointsTooSmallException();
        }
        this.programmes = new HashMap<>();
        this.courses = new HashMap<>();
        this.scholarships = new HashMap<>();
        this.name = name;
        this.minimumCreditPoints = minimumCreditPoints;
        this.maximumCreditPoints = maximumCreditPoints;
    }

    /**
     * Instantiates a new University.
     *
     * @param name the name
     */
    public University(String name) {
        this(name, DEFAULT_MINIMUM_CREDIT_POINTS, DEFAULT_MAXIMUM_CREDIT_POINTS);
    }

    /**
     * Create a new study programme.
     *
     * @param name of study programme
     * @return the study programme
     */
    public StudyProgramme createProgramme(String name) {
        if (programmes.containsKey(name)) {
            throw new ProgrammeExistsException();
        }
        StudyProgramme studyProgramme = new StudyProgramme(this, name);
        programmes.put(name, studyProgramme);
        return studyProgramme;
    }

    /**
     * Add course.
     *
     * @param course to add
     */
    public void addCourse(Course course) {
        courses.put(course.getName(), course);
    }

    /**
     * Add scholarship.
     *
     * @param scholarship to add
     */
    public void addScholarship(Scholarship scholarship) {
        scholarships.put(scholarship.getId(), scholarship);
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses.values());
    }

    public List<Student> getStudents() {
        return programmes.values().stream()
            .map(StudyProgramme::getStudents)
            .flatMap(Collection::stream)
            .distinct()
            .collect(Collectors.toList());
    }

    public List<Student> getStudyingStudents() {
        return this.getStudents().stream()
            .filter(Student::currentlyStudying)
            .collect(Collectors.toList());
    }

    public List<Student> getSortedStudents() {
        return getStudents().stream().sorted().collect(Collectors.toList());
    }

    public int getMinimumCreditPoints() {
        return minimumCreditPoints;
    }

    public int getMaximumCreditPoints() {
        return maximumCreditPoints;
    }


}
