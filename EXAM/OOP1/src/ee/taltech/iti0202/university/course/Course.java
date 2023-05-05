package ee.taltech.iti0202.university.course;

import ee.taltech.iti0202.university.entity.Teacher;
import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.declaration.Declaration;
import ee.taltech.iti0202.university.exceptions.CreditPointsNotPositiveException;

import java.util.HashMap;

public class Course {
    public enum GradingType {
        GRADED, NON_GRADED
    }

    private University university;
    private Teacher teacher;
    private HashMap<Integer, Declaration> declarations;
    private HashMap<String, StudyProgramme> studyProgrammes;
    private String name;
    private int creditPoints;
    private GradingType gradingType;


    /**
     * Instantiates a new Course.
     *
     * @param teacher      the teacher
     * @param name         the name
     * @param university   the university
     * @param creditPoints the credit points
     * @param gradingType  the grading type
     */
    public Course(Teacher teacher, String name, University university, int creditPoints, GradingType gradingType) {
        if (creditPoints <= 0) {
            throw new CreditPointsNotPositiveException();
        }
        this.teacher = teacher;
        this.declarations = new HashMap<>();
        this.studyProgrammes = new HashMap<>();
        this.name = name;
        this.university = university;
        this.creditPoints = creditPoints;
        this.gradingType = gradingType;
        this.teacher.addCourse(this);
        this.university.addCourse(this);
    }

    /**
     * Instantiates a new Course.
     *
     * @param teacher    the teacher
     * @param name       the name
     * @param university the university
     */
    public Course(Teacher teacher, String name, University university) {
        this(teacher, name, university, 6, GradingType.GRADED);
    }

    public String getName() {
        return name;
    }

    public University getUniversity() {
        return university;
    }

    public int getCreditPoints() {
        return creditPoints;
    }

    public GradingType getGradingType() {
        return gradingType;
    }

    public HashMap<String, StudyProgramme> getStudyProgrammes() {
        return studyProgrammes;
    }

    /**
     * Add declaration.
     *
     * @param declaration to add
     */
    public void addDeclaration(Declaration declaration) {
        declarations.put(declaration.getId(), declaration);
    }

    public int getDeclarationAmount() {
        return declarations.values().size();
    }
}
