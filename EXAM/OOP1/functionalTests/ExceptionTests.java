import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.Scholarship;
import ee.taltech.iti0202.university.entity.Student;
import ee.taltech.iti0202.university.course.StudyProgramme;
import ee.taltech.iti0202.university.entity.Teacher;
import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.exceptions.CreditPointsNotPositiveException;
import ee.taltech.iti0202.university.exceptions.IllegalGradeException;
import ee.taltech.iti0202.university.exceptions.MaximumCreditPointsTooSmallException;
import ee.taltech.iti0202.university.exceptions.MinimumCreditPointsNegativeException;
import ee.taltech.iti0202.university.exceptions.MonetaryAmountNotPositiveException;
import ee.taltech.iti0202.university.exceptions.NoSuchDeclarationException;
import ee.taltech.iti0202.university.exceptions.ProgrammeExistsException;
import ee.taltech.iti0202.university.exceptions.StudentAmountNotPositiveException;
import ee.taltech.iti0202.university.exceptions.StudentTooYoungException;
import ee.taltech.iti0202.university.exceptions.StudyPlanActiveException;
import ee.taltech.iti0202.university.exceptions.StudyPlanNotFinishedException;
import ee.taltech.iti0202.university.exceptions.UniversityMissingException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ExceptionTests {

    public static final int STUDENT_AGE = 20;
    public static final int CREDIT_POINTS = 3;
    public static final int TOO_LARGE_GRADE = 6;
    public static final int PASSING_GRADE = 1;
    public static final int MAXIMUM_CREDIT_POINTS = 30;
    public static final int TOO_FEW_CREDIT_POINTS = -1;

    @Test
    public void testCourseWithZeroCreditPointsThrowsException() {
        University university = new University("TalTech");
        Teacher teacher = new Teacher("Kalda");
        Assertions.assertThrows(CreditPointsNotPositiveException.class, () -> new Course(
            teacher,
            "Quantum mechanics",
            university,
            0,
            Course.GradingType.NON_GRADED
        ));
    }

    @Test
    public void testScholarshipWithZeroMoneyThrowsException() {
        University university = new University("TalTech");
        Assertions.assertThrows(MonetaryAmountNotPositiveException.class, () -> new Scholarship.ScholarshipBuilder()
            .withUniversity(university)
            .withStudentAmount(1)
            .build());
    }

    @Test
    public void testScholarshipWithZeroStudentsThrowsException() {
        University university = new University("TalTech");
        Assertions.assertThrows(StudentAmountNotPositiveException.class, () -> new Scholarship.ScholarshipBuilder()
            .withUniversity(university)
            .withMonetaryAmount(1)
            .build());
    }

    @Test
    public void testScholarshipWithNoUniversityThrowsException() {
        Assertions.assertThrows(UniversityMissingException.class, () -> new Scholarship.ScholarshipBuilder()
            .withStudentAmount(1)
            .withMonetaryAmount(1)
            .build());
    }

    @Test
    public void testTooYoungStudentThrowsException() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Assertions.assertThrows(StudentTooYoungException.class, () -> new Student(
            studyProgramme,
            "Kaarel",
            Student.AGE_LIMIT - 1
        ));
    }

    @Test
    public void testUniversityWithNegativeMinimumCreditPointsThrowsException() {
        Assertions.assertThrows(MinimumCreditPointsNegativeException.class, () -> new University(
            "TalTech",
            TOO_FEW_CREDIT_POINTS,
            MAXIMUM_CREDIT_POINTS
        ));
    }

    @Test
    public void testUniversityWithMinimumAndMaximumCreditPointsEqualThrowsException() {
        Assertions.assertThrows(MaximumCreditPointsTooSmallException.class, () -> new University(
            "TalTech",
            MAXIMUM_CREDIT_POINTS,
            MAXIMUM_CREDIT_POINTS
        ));
    }

    @Test
    public void testCreatingStudyProgrammeWithExistingNameThrowsException() {
        University university = new University("TalTech");
        university.createProgramme("Physics");
        Assertions.assertThrows(ProgrammeExistsException.class, () -> university.createProgramme("Physics"));
    }

    @Test
    public void testGivingIllegalGradeThrowsException() {
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
        Assertions.assertThrows(
            IllegalGradeException.class,
            () -> teacher.grade(student, "Quantum mechanics", TOO_LARGE_GRADE)
        );
    }

    @Test
    public void testTeacherGivingGradeWhenNoDeclarationThrowsException() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        Assertions.assertThrows(
            NoSuchDeclarationException.class,
            () -> teacher.grade(student, "Quantum mechanics", PASSING_GRADE)
        );
    }

    @Test
    public void testDeclaringCourseWithActiveStudyPlanThrowsException() {
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
        student.activateStudyPlan();
        Assertions.assertThrows(StudyPlanActiveException.class, () -> student.declareCourse(course));
    }

    @Test
    public void testStartingNewStudyPlanWithUngradedCoursesThrowsException() {
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
        Assertions.assertThrows(StudyPlanNotFinishedException.class, student::makeNewStudyPlan);
    }
}
