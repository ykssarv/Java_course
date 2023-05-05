
import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.entity.Student;
import ee.taltech.iti0202.university.course.StudyProgramme;
import ee.taltech.iti0202.university.entity.Teacher;
import ee.taltech.iti0202.university.University;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class DeclarationAndGradingTests {

    public static final int STUDENT_AGE = 20;
    public static final int HALF_OF_PROGRAMME_IN_PERCENTAGES = 50;
    public static final int PASSING_GRADE = 1;
    public static final int FAILING_GRADE = 0;
    public static final int CREDIT_POINTS = 3;

    @Test
    public void testTeacherCanSeeCourses() {
        University university = new University("TalTech");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(teacher, "Quantum mechanics", university);
        Course course2 = new Course(teacher, "Thermodynamics", university);
        List<Course> courses = teacher.getCourses();
        Assertions.assertTrue(courses.contains(course));
        Assertions.assertTrue(courses.contains(course2));
    }

    @Test
    public void testStudentCanPassCourse() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(
            teacher,
            "Quantum mechanics",
            university,
            CREDIT_POINTS,
            Course.GradingType.NON_GRADED
        );
        studyProgramme.createModule("Physics");
        studyProgramme.addCourse(course, "Physics");
        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        student.declareCourse(course);
        student.activateStudyPlan();
        teacher.grade(student, "Quantum mechanics", PASSING_GRADE);
        Assertions.assertTrue(student.hasPassed("Quantum mechanics"));
    }

    @Test
    public void testStudentCanGetGradeForCourse() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(teacher, "Quantum mechanics", university);
        studyProgramme.createModule("Physics");
        studyProgramme.addCourse(course, "Physics");
        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        student.declareCourse(course);
        student.activateStudyPlan();
        teacher.grade(student, "Quantum mechanics", PASSING_GRADE);
        Assertions.assertEquals(PASSING_GRADE, student.getGrade("Quantum mechanics"));
    }

    @Test
    public void testStudentCanCheckPassedCourses() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(teacher, "Quantum mechanics", university);
        Course course2 = new Course(teacher, "Thermodynamics", university);
        studyProgramme.createModule("Physics");
        studyProgramme.addCourse(course, "Physics");
        studyProgramme.addCourse(course2, "Physics");
        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        student.declareCourse(course);
        student.declareCourse(course2);
        student.activateStudyPlan();
        teacher.grade(student, "Quantum mechanics", PASSING_GRADE);
        teacher.grade(student, "Thermodynamics", FAILING_GRADE);
        List<Course> passed = student.getPassedCourses();
        Assertions.assertTrue(passed.contains(course));
        Assertions.assertEquals(1, passed.size());
    }

    @Test
    public void testStudentCanCheckFailedCourses() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(teacher, "Quantum mechanics", university);
        Course course2 = new Course(teacher, "Thermodynamics", university);
        studyProgramme.createModule("Physics");
        studyProgramme.addCourse(course, "Physics");
        studyProgramme.addCourse(course2, "Physics");
        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        student.declareCourse(course);
        student.declareCourse(course2);
        student.activateStudyPlan();
        teacher.grade(student, "Quantum mechanics", PASSING_GRADE);
        teacher.grade(student, "Thermodynamics", FAILING_GRADE);
        List<Course> failed = student.getFailedCourses();
        Assertions.assertTrue(failed.contains(course2));
        Assertions.assertEquals(1, failed.size());
    }

    @Test
    public void testStudentCanCheckStudyProgrammeCompletion() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(teacher, "Quantum mechanics", university);
        Course course2 = new Course(teacher, "Thermodynamics", university);
        studyProgramme.createModule("Physics");
        studyProgramme.addCourse(course, "Physics");
        studyProgramme.addCourse(course2, "Physics");
        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        student.declareCourse(course);
        student.activateStudyPlan();
        teacher.grade(student, "Quantum mechanics", PASSING_GRADE);
        double studyProgrammeCompletion = student.getStudyProgrammeCompletion();
        Assertions.assertEquals(HALF_OF_PROGRAMME_IN_PERCENTAGES, studyProgrammeCompletion);
    }
}
