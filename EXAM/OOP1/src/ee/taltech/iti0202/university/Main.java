package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.course.Course;
import ee.taltech.iti0202.university.course.StudyProgramme;
import ee.taltech.iti0202.university.declaration.Declaration;
import ee.taltech.iti0202.university.entity.Student;
import ee.taltech.iti0202.university.entity.Teacher;
import ee.taltech.iti0202.university.exceptions.CreditPointsNotPositiveException;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static final int STUDENT_AGE = 20;
    public static final int MANY_CREDIT_POINTS = 9;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        University university1 = new University("TalTech");
        StudyProgramme studyProgramme = university1.createProgramme("Physics");

        Student student1 = new Student(studyProgramme, "Kaarel", STUDENT_AGE);
        Student student2 = new Student(studyProgramme, "Karel", STUDENT_AGE);
        Student student3 = new Student(studyProgramme, "Karl", STUDENT_AGE);

        // Adding students to university is not necessary / possible,
        // because a student is assigned a university when the student is made.

        University university2 = new University("Tartu");

        // Students can not be in several universities at once,
        // because it simply is not possible, given the architecture.

        Teacher teacher = new Teacher("Kalda");
        Course course1 = new Course(teacher, "Thermodynamics", university1);
        Course course2 = new Course(
            teacher,
            "Quantum mechanics",
            university1,
            MANY_CREDIT_POINTS,
            Course.GradingType.NON_GRADED
        );

        try {
            new Course(
                teacher,
                "Electromagnetism",
                university1,
                0,
                Course.GradingType.GRADED
            );
        } catch (CreditPointsNotPositiveException ignored) {

        }

        student1.declareCourse(course1);
        student1.declareCourse(course2);
        student2.declareCourse(course1);
        student2.declareCourse(course2);

        // Grade is converted to boolean when grading non-graded course.
        teacher.grade(student1, "Thermodynamics", 5);
        teacher.grade(student1, "Quantum mechanics", 5);
        teacher.grade(student2, "Thermodynamics", 0);
        teacher.grade(student2, "Quantum mechanics", 0);

        List<String> results = student1.getGradedDeclarations().stream()
            .map(Declaration::toString)
            .collect(Collectors.toList());

        List<String> students = university1.getStudents().stream()
            .map(Student::getName)
            .collect(Collectors.toList());

        List<String> courses = university1.getCourses().stream()
            .map(Course::getName)
            .collect(Collectors.toList());

        List<String> studyingStudents = university1.getStudyingStudents().stream()
            .map(Student::getName)
            .collect(Collectors.toList());

        System.out.println(results);
        System.out.println(students);
        System.out.println(courses);
        System.out.println(studyingStudents);
    }
}
