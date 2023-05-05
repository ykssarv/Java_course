import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.entity.Student;
import ee.taltech.iti0202.university.course.StudyProgramme;
import ee.taltech.iti0202.university.entity.Teacher;
import ee.taltech.iti0202.university.University;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class MinimumRequirements {

    public static final int STUDENT_AGE = 20;
    public static final int COURSE_CREDIT_POINTS = 12;

    @Test
    public void testUniversityHasCourses() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(teacher, "Quantum mechanics", university);
        Course course2 = new Course(teacher, "Thermodynamics", university);
        studyProgramme.createModule("Physics");
        studyProgramme.addCourse(course, "Physics");
        studyProgramme.addCourse(course2, "Physics");
        List<Course> courses = university.getCourses();
        Assertions.assertTrue(courses.contains(course));
        Assertions.assertTrue(courses.contains(course2));
    }

    @Test
    public void testStudentHasNameAndAge() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        Assertions.assertEquals("Kaarel", student.getName());
        Assertions.assertEquals(STUDENT_AGE, student.getAge());
    }

    @Test
    public void testStudentCanEnterUniversity() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Student student = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        Assertions.assertEquals(university, student.getUniversity());
    }

    @Test
    public void testCourseHasRequiredParameters() {
        University university = new University("TalTech");
        Teacher teacher = new Teacher("Kalda");
        Course course = new Course(
            teacher,
            "Quantum mechanics",
            university,
            COURSE_CREDIT_POINTS,
            Course.GradingType.NON_GRADED
        );
        Assertions.assertEquals("Quantum mechanics", course.getName());
        Assertions.assertEquals(COURSE_CREDIT_POINTS, course.getCreditPoints());
        Assertions.assertEquals(Course.GradingType.NON_GRADED, course.getGradingType());
        Assertions.assertEquals(university, course.getUniversity());
    }

    @Test
    public void testUniversityHasStudents() {
        University university = new University("TalTech");
        StudyProgramme studyProgramme = university.createProgramme("Physics");
        Student student = new Student(studyProgramme, "Karel", STUDENT_AGE);
        StudyProgramme studyProgramme2 = university.createProgramme("Informatics");
        Student student2 = new Student(studyProgramme2, "Marel", STUDENT_AGE);
        List<Student> students = university.getStudents();
        Assertions.assertTrue(students.contains(student));
        Assertions.assertTrue(students.contains(student2));
    }
}
