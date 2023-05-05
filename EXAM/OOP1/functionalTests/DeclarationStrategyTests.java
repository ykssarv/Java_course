import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.entity.Student;
import ee.taltech.iti0202.university.course.StudyProgramme;
import ee.taltech.iti0202.university.entity.Teacher;
import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.declaration.strategy.BasicDeclarationStrategy;
import ee.taltech.iti0202.university.declaration.Declaration;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.stream.Collectors;

public class DeclarationStrategyTests {

    public static final int STUDENT_AGE = 20;
    public static final int MINIMUM_CREDIT_POINTS = 3;
    public static final int MAXIMUM_CREDIT_POINTS = 30;

    @Test
    public void testChooseEasyCoursesFirstChoosesEasierCourses() {
        University university = new University("TalTech", MINIMUM_CREDIT_POINTS, MAXIMUM_CREDIT_POINTS);
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(
            teacher,
            "Quantum mechanics",
            university,
            MINIMUM_CREDIT_POINTS + 1,
            Course.GradingType.GRADED
        );
        Course course2 = new Course(
            teacher,
            "Thermodynamics",
            university,
            MINIMUM_CREDIT_POINTS,
            Course.GradingType.GRADED
        );
        studyProgramme.createModule("Physics");
        studyProgramme.addCourse(course, "Physics");
        studyProgramme.addCourse(course2, "Physics");
        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);

        student.makeNewStudyPlan(BasicDeclarationStrategy.StrategyType.EASY);
        List<Declaration> declarations = student.getActiveDeclarations();
        Assertions.assertEquals(1, declarations.size());
        Assertions.assertEquals(course2, declarations.get(0).getCourse());
    }

    @Test
    public void testTakeFromDifferentModulesTakesCoursesFromDifferentModules() {
        University university = new University("TalTech", MINIMUM_CREDIT_POINTS, MAXIMUM_CREDIT_POINTS);
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(
            teacher,
            "Quantum mechanics",
            university,
            MINIMUM_CREDIT_POINTS,
            Course.GradingType.GRADED
        );
        Course course2 = new Course(
            teacher,
            "Thermodynamics",
            university,
            MINIMUM_CREDIT_POINTS,
            Course.GradingType.GRADED
        );
        studyProgramme.createModule("Physics");
        studyProgramme.createModule("Mathematics");
        studyProgramme.addCourse(course, "Mathematics");
        studyProgramme.addCourse(course2, "Physics");
        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);

        student.makeNewStudyPlan(BasicDeclarationStrategy.StrategyType.DIFFERENT);
        List<Course> courses = student.getActiveDeclarations().stream()
            .map(Declaration::getCourse)
            .collect(Collectors.toList());
        Assertions.assertEquals(2, courses.size());
        Assertions.assertTrue(courses.contains(course));
        Assertions.assertTrue(courses.contains(course2));
    }

    @Test
    public void testPopularCourseStrategyTakesPopularCoursesFirst() {
        University university = new University("TalTech", MINIMUM_CREDIT_POINTS, MINIMUM_CREDIT_POINTS + 1);
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(
            teacher,
            "Quantum mechanics",
            university,
            MINIMUM_CREDIT_POINTS,
            Course.GradingType.GRADED
        );
        Course course2 = new Course(
            teacher,
            "Thermodynamics",
            university,
            MINIMUM_CREDIT_POINTS,
            Course.GradingType.GRADED
        );
        studyProgramme.createModule("Physics");
        studyProgramme.createModule("Mathematics");
        studyProgramme.addCourse(course, "Mathematics");
        studyProgramme.addCourse(course2, "Physics");
        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        Student student2 = new Student(studyProgramme, "Karel", STUDENT_AGE);
        student2.declareCourse(course2);

        student.makeNewStudyPlan(BasicDeclarationStrategy.StrategyType.POPULAR);
        List<Course> courses = student.getActiveDeclarations().stream()
            .map(Declaration::getCourse)
            .collect(Collectors.toList());
        Assertions.assertEquals(1, courses.size());
        Assertions.assertTrue(courses.contains(course2));
    }

    @Test
    public void testStrategyToTakeFromDifferentModulesTakesExtraCoursesIfNotEnoughForMinimumAmount() {
        University university = new University("TalTech", MINIMUM_CREDIT_POINTS * 3, MAXIMUM_CREDIT_POINTS);
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(
            teacher,
            "Quantum mechanics",
            university,
            MINIMUM_CREDIT_POINTS,
            Course.GradingType.GRADED
        );
        Course course2 = new Course(
            teacher,
            "Thermodynamics",
            university,
            MINIMUM_CREDIT_POINTS,
            Course.GradingType.GRADED
        );
        Course course3 = new Course(
            teacher,
            "Electromagnetism",
            university,
            MINIMUM_CREDIT_POINTS,
            Course.GradingType.GRADED
        );
        studyProgramme.createModule("Physics");
        studyProgramme.createModule("Mathematics");
        studyProgramme.addCourse(course, "Mathematics");
        studyProgramme.addCourse(course2, "Physics");
        studyProgramme.addCourse(course3, "Physics");
        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);

        student.makeNewStudyPlan(BasicDeclarationStrategy.StrategyType.DIFFERENT);
        List<Course> courses = student.getActiveDeclarations().stream()
            .map(Declaration::getCourse)
            .collect(Collectors.toList());
        Assertions.assertEquals(3, courses.size());
        Assertions.assertTrue(courses.contains(course));
        Assertions.assertTrue(courses.contains(course2));
        Assertions.assertTrue(courses.contains(course3));
    }
}
