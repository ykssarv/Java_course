import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.Scholarship;
import ee.taltech.iti0202.university.entity.Student;
import ee.taltech.iti0202.university.course.StudyProgramme;
import ee.taltech.iti0202.university.entity.Teacher;
import ee.taltech.iti0202.university.University;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class ScholarshipAndOrderingTests {

    public static final int STUDENT_AGE = 20;
    public static final int BAD_GRADE = 1;
    public static final int GOOD_GRADE = 3;
    public static final int MINIMUM_CREDIT_POINTS_FOR_SCHOLARSHIP = 7;

    @Test
    public void testOnlyOneStudentGetsScholarship() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(teacher, "Quantum mechanics", university);
        studyProgramme.createModule("Physics");
        studyProgramme.addCourse(course, "Physics");

        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        student.declareCourse(course);
        student.activateStudyPlan();


        Student student2 = new Student(studyProgramme, "Karel", STUDENT_AGE);
        student2.declareCourse(course);
        student2.activateStudyPlan();

        teacher.grade(student, "Quantum mechanics", BAD_GRADE);
        teacher.grade(student2, "Quantum mechanics", GOOD_GRADE);

        Scholarship scholarship = new Scholarship.ScholarshipBuilder()
            .withUniversity(university)
            .withStudentAmount(1)
            .withMonetaryAmount(1)
            .build();
        scholarship.grant();

        Assertions.assertEquals(1, student2.getAllScholarships().size());
        Assertions.assertEquals(0, student.getAllScholarships().size());
    }

    @Test
    public void testOnlyStudentsSatisfyingMinimumAverageGradeLimitGetScholarship() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(teacher, "Quantum mechanics", university);
        studyProgramme.createModule("Physics");
        studyProgramme.addCourse(course, "Physics");

        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        student.declareCourse(course);
        student.activateStudyPlan();


        Student student2 = new Student(studyProgramme, "Karel", STUDENT_AGE);
        student2.declareCourse(course);
        student2.activateStudyPlan();

        teacher.grade(student, "Quantum mechanics", BAD_GRADE);
        teacher.grade(student2, "Quantum mechanics", GOOD_GRADE);

        Scholarship scholarship = new Scholarship.ScholarshipBuilder()
            .withUniversity(university)
            .withStudentAmount(2)
            .withMinimumAverageGrade(2)
            .withMonetaryAmount(1)
            .build();
        scholarship.grant();

        Assertions.assertEquals(1, student2.getAllScholarships().size());
        Assertions.assertEquals(0, student.getAllScholarships().size());
    }

    @Test
    public void testNoStudentsGetScholarship() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(teacher, "Quantum mechanics", university);
        studyProgramme.createModule("Physics");
        studyProgramme.addCourse(course, "Physics");

        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        student.declareCourse(course);
        student.activateStudyPlan();


        Student student2 = new Student(studyProgramme, "Karel", STUDENT_AGE);
        student2.declareCourse(course);
        student2.activateStudyPlan();

        teacher.grade(student, "Quantum mechanics", BAD_GRADE);
        teacher.grade(student2, "Quantum mechanics", GOOD_GRADE);

        Scholarship scholarship = new Scholarship.ScholarshipBuilder()
            .withUniversity(university)
            .withStudentAmount(2)
            .withMinimumAverageGrade(2)
            .withMonetaryAmount(1)
            .withMinimumCreditPoints(MINIMUM_CREDIT_POINTS_FOR_SCHOLARSHIP)
            .build();
        scholarship.grant();

        Assertions.assertEquals(0, student2.getAllScholarships().size());
        Assertions.assertEquals(0, student.getAllScholarships().size());
    }

    @Test
    public void testHigherGradedStudentComesBeforeInOrdering() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(teacher, "Quantum mechanics", university);
        studyProgramme.createModule("Physics");
        studyProgramme.addCourse(course, "Physics");

        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        student.declareCourse(course);
        student.activateStudyPlan();


        Student student2 = new Student(studyProgramme, "Karel", STUDENT_AGE);
        student2.declareCourse(course);
        student2.activateStudyPlan();

        teacher.grade(student, "Quantum mechanics", BAD_GRADE);
        teacher.grade(student2, "Quantum mechanics", GOOD_GRADE);

        List<Student> orderedStudents = university.getSortedStudents();

        Assertions.assertEquals(student2, orderedStudents.get(0));
        Assertions.assertEquals(student, orderedStudents.get(1));
    }

    @Test
    public void testStudentWithHigherCompletionPercentageComesBeforeInOrderWhenSameAverageGrade() {
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


        Student student2 = new Student(studyProgramme, "Karel", STUDENT_AGE);
        student2.declareCourse(course);
        student2.declareCourse(course2);
        student2.activateStudyPlan();

        teacher.grade(student, "Quantum mechanics", GOOD_GRADE);
        teacher.grade(student2, "Quantum mechanics", GOOD_GRADE);
        teacher.grade(student2, "Thermodynamics", GOOD_GRADE);

        List<Student> orderedStudents = university.getSortedStudents();

        Assertions.assertEquals(student2, orderedStudents.get(0));
        Assertions.assertEquals(student, orderedStudents.get(1));
    }

    @Test
    public void testStudentWithMoreCreditPointsComesBeforeInOrderWhenOtherThingsSame() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Simple physics");
        StudyProgramme studyProgramme2 = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(teacher, "Quantum mechanics", university);
        Course course2 = new Course(teacher, "Thermodynamics", university);

        studyProgramme.createModule("Physics");
        studyProgramme.addCourse(course, "Physics");

        studyProgramme2.createModule("Physics");
        studyProgramme2.addCourse(course, "Physics");
        studyProgramme2.addCourse(course2, "Physics");

        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        student.declareCourse(course);
        student.activateStudyPlan();


        Student student2 = new Student(studyProgramme2, "Karel", STUDENT_AGE);
        student2.declareCourse(course);
        student2.declareCourse(course2);
        student2.activateStudyPlan();

        teacher.grade(student, "Quantum mechanics", GOOD_GRADE);
        teacher.grade(student2, "Quantum mechanics", GOOD_GRADE);
        teacher.grade(student2, "Thermodynamics", GOOD_GRADE);

        List<Student> orderedStudents = university.getSortedStudents();

        Assertions.assertEquals(student2, orderedStudents.get(0));
        Assertions.assertEquals(student, orderedStudents.get(1));
    }

    @Test
    public void testStudentsComeInAlphabeticalOrderWhenEverythingElseEqual() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(teacher, "Quantum mechanics", university);
        studyProgramme.createModule("Physics");
        studyProgramme.addCourse(course, "Physics");

        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        student.declareCourse(course);
        student.activateStudyPlan();

        Student student2 = new Student(studyProgramme, "Anna", STUDENT_AGE);
        student2.declareCourse(course);
        student2.activateStudyPlan();

        teacher.grade(student, "Quantum mechanics", GOOD_GRADE);
        teacher.grade(student2, "Quantum mechanics", GOOD_GRADE);

        List<Student> orderedStudents = university.getSortedStudents();

        Assertions.assertEquals(student2, orderedStudents.get(0));
        Assertions.assertEquals(student, orderedStudents.get(1));
    }
}
