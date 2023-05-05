package ee.taltech.iti0202.university.entity;

import ee.taltech.iti0202.university.Scholarship;
import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.StudyProgramme;
import ee.taltech.iti0202.university.declaration.StudyPlan;
import ee.taltech.iti0202.university.declaration.strategy.BasicDeclarationStrategy;
import ee.taltech.iti0202.university.declaration.Declaration;
import ee.taltech.iti0202.university.declaration.strategy.EasyCoursesStrategy;
import ee.taltech.iti0202.university.declaration.strategy.PopularCoursesStrategy;
import ee.taltech.iti0202.university.declaration.strategy.TakeFromEachModuleStrategy;
import ee.taltech.iti0202.university.exceptions.DeclarationDoesNotHaveGradeException;
import ee.taltech.iti0202.university.exceptions.NoSuchDeclarationException;
import ee.taltech.iti0202.university.exceptions.StudentTooYoungException;
import ee.taltech.iti0202.university.exceptions.StudyPlanActiveException;
import ee.taltech.iti0202.university.exceptions.StudyPlanNotFinishedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student implements Comparable {
    public static final int AGE_LIMIT = 16;
    public static final double HALF = 0.5;
    private String name;
    private int age;
    private StudyPlan studyPlan;
    private List<StudyPlan> oldStudyPlans;
    private StudyProgramme studyProgramme;
    private HashMap<Integer, Scholarship> scholarships;
    private Integer id;
    private static int count = 0;

    /**
     * Instantiates a new Student.
     *
     * @param studyProgramme the study programme
     * @param name           the name
     * @param age            the age
     */
    public Student(StudyProgramme studyProgramme, String name, int age) {
        if (age < AGE_LIMIT) {
            throw new StudentTooYoungException();
        }
        this.name = name;
        this.age = age;
        this.studyProgramme = studyProgramme;
        this.studyPlan = new StudyPlan(this);
        count += 1;
        this.id = count;
        this.scholarships = new HashMap<>();
        this.oldStudyPlans = new ArrayList<>();
        studyProgramme.addStudent(this);
    }

    /**
     * Add scholarship to student.
     *
     * @param scholarship
     */
    public void addScholarship(Scholarship scholarship) {
        scholarships.put(scholarship.getId(), scholarship);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public University getUniversity() {
        return this.studyProgramme.getUniversity();
    }

    public StudyProgramme getStudyProgramme() {
        return this.studyProgramme;
    }

    /**
     * Declare course.
     *
     * @param course
     */
    public void declareCourse(Course course) {
        if (studyPlan.isActive()) {
            throw new StudyPlanActiveException();
        }
        studyPlan.addDeclaration(course);
    }

    /**
     * Gets declaration by course.
     *
     * @param course
     * @return optional of declaration
     */
    public Optional<Declaration> getDeclarationByCourse(Course course) {
        return studyPlan.getDeclarationByCourse(course);
    }

    /**
     * Check if the student is currently studying.
     *
     * @return the boolean
     */
    public boolean currentlyStudying() {
        return studyPlan.isActive() && studyPlan.hasActiveDeclarations();
    }

    /**
     * Activate study plan.
     */
    public void activateStudyPlan() {
        studyPlan.activate();
    }

    /**
     * Start making a new study plan.
     */
    public void makeNewStudyPlan() {
        if (studyPlan.hasActiveDeclarations()) {
            throw new StudyPlanNotFinishedException();
        }
        oldStudyPlans.add(studyPlan);
        studyPlan.deactivate();
        studyPlan = new StudyPlan(this);
    }

    /**
     * Start making a new study plan with the given strategy.
     *
     * @param strategyType to use
     */
    public void makeNewStudyPlan(BasicDeclarationStrategy.StrategyType strategyType) {
        makeNewStudyPlan();
        BasicDeclarationStrategy strategy;
        if (strategyType == BasicDeclarationStrategy.StrategyType.EASY) {
            strategy = new EasyCoursesStrategy(this);
        } else if (strategyType == BasicDeclarationStrategy.StrategyType.DIFFERENT) {
            strategy = new TakeFromEachModuleStrategy(this);
        } else {
            strategy = new PopularCoursesStrategy(this);
        }

        strategy.findFinalCourses(
            getUniversity().getMinimumCreditPoints(),
            getUniversity().getMaximumCreditPoints()
        ).forEach(this::declareCourse);
    }

    public int getCompletedCreditPoints() {
        return Stream.concat(oldStudyPlans.stream(), Stream.of(studyPlan))
            .mapToInt(StudyPlan::getTotalCreditPoints)
            .sum();
    }

    /**
     * Get percentage of study programme completion.
     *
     * @return percentage
     */
    public double getStudyProgrammeCompletion() {
        int totalCreditPoints = studyProgramme.getTotalCreditPoints();
        int completedCreditPoints = getCompletedCreditPoints();
        return 100d * completedCreditPoints / totalCreditPoints;
    }

    public List<Declaration> getAllDeclarations() {
        return Stream.concat(oldStudyPlans.stream(), Stream.of(studyPlan))
            .map(StudyPlan::getDeclarations)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    public List<Declaration> getActiveDeclarations() {
        return studyPlan.getDeclarations();
    }

    /**
     * Find declaration for course with given name.
     *
     * @param name of the course
     * @return optional of course
     */
    public Optional<Declaration> findDeclaration(String name) {
        return getAllDeclarations().stream()
            .filter(x -> x.getCourse().getName().equals(name))
            .findFirst();
    }

    /**
     * Check if student has passed course with the given name.
     *
     * @param name of the course
     * @return boolean
     */
    public boolean hasPassed(String name) {
        Optional<Declaration> declaration = findDeclaration(name);
        if (declaration.isEmpty()) {
            return false;
        }
        return declaration.get().isPassed();
    }

    /**
     * Gets grade for course with given name.
     *
     * @param name of the course
     * @return the grade for that course
     */
    public int getGrade(String name) {
        Optional<Declaration> declaration = findDeclaration(name);
        if (declaration.isEmpty()) {
            throw new NoSuchDeclarationException();
        }
        if (declaration.get().getCourse().getGradingType() == Course.GradingType.NON_GRADED) {
            throw new DeclarationDoesNotHaveGradeException();
        }
        if (!declaration.get().isFinished()) {
            throw new DeclarationDoesNotHaveGradeException();
        }
        return declaration.get().getGrade();
    }

    public List<Scholarship> getAllScholarships() {
        return new ArrayList<>(scholarships.values());
    }

    public List<Course> getPassedCourses() {
        return getAllDeclarations().stream()
            .filter(x -> x.isFinished() && x.isPassed())
            .map(Declaration::getCourse)
            .collect(Collectors.toList());
    }

    public List<Course> getFailedCourses() {
        return getAllDeclarations().stream()
            .filter(x -> x.isFinished() && !x.isPassed())
            .map(Declaration::getCourse)
            .collect(Collectors.toList());
    }

    public List<Declaration> getGradedDeclarations() {
        return getAllDeclarations().stream()
            .filter(Declaration::isFinished)
            .collect(Collectors.toList());
    }

    /**
     * Calculate average grade for student.
     *
     * @return average grade
     */
    public double averageGrade() {
        List<Declaration> finishedDeclarations = this.getGradedDeclarations();

        List<Declaration> passedDeclarations = finishedDeclarations.stream()
            .filter(Declaration::isPassed)
            .collect(Collectors.toList());

        List<Declaration> failedDeclarations = finishedDeclarations.stream()
            .filter(x -> !x.isPassed())
            .collect(Collectors.toList());

        double totalCreditPoints = passedDeclarations.stream().mapToInt(x -> x.getCourse().getCreditPoints()).sum();
        totalCreditPoints += HALF * failedDeclarations.stream().mapToInt(x -> x.getCourse().getCreditPoints()).sum();

        double weightedGrade = passedDeclarations.stream()
            .mapToInt(x -> x.getGrade() * x.getCourse().getCreditPoints())
            .sum();
        return weightedGrade / totalCreditPoints;
    }

    @Override
    public int compareTo(Object o) {
        Student other = (Student) o;
        int myGrade = (int) Math.floor(10 * this.averageGrade());
        int otherGrade = (int) Math.floor(10 * other.averageGrade());
        if (myGrade < otherGrade) {
            return 1;
        }
        if (otherGrade < myGrade) {
            return -1;
        }

        double myCompletion = this.getStudyProgrammeCompletion();
        double otherCompletion = other.getStudyProgrammeCompletion();
        if (myCompletion < otherCompletion) {
            return 1;
        }
        if (otherCompletion < myCompletion) {
            return -1;
        }

        int myCreditPoints = this.getCompletedCreditPoints();
        int otherCreditPoints = other.getCompletedCreditPoints();
        if (myCreditPoints < otherCreditPoints) {
            return 1;
        }
        if (otherCreditPoints < myCreditPoints) {
            return -1;
        }
        return this.getName().compareTo(other.getName());
    }
}
