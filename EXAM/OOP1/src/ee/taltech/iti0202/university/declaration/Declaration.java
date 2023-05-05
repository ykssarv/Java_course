package ee.taltech.iti0202.university.declaration;

import ee.taltech.iti0202.university.course.Course;

public class Declaration {
    private Course course;
    private StudyPlan studyPlan;
    private Integer id;
    private static int count = 0;
    private int grade;
    private boolean passed;
    private boolean finished;

    /**
     * Instantiates a new Declaration.
     *
     * @param course    the course
     * @param studyPlan the study plan
     */
    public Declaration(Course course, StudyPlan studyPlan) {
        this.course = course;
        this.studyPlan = studyPlan;
        count += 1;
        this.id = count;
        this.finished = false;
        course.addDeclaration(this);
    }

    public Integer getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public int getGrade() {
        return grade;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isPassed() {
        return passed;
    }

    /**
     * Add a grade in a pass / fail course.
     *
     * @param passed
     */
    public void grade(boolean passed) {
        this.passed = passed;
        this.finished = true;
    }

    /**
     * Add grade to declaration.
     *
     * @param grade to give
     */
    public void grade(int grade) {
        this.grade = grade;
        this.passed = grade > 0;
        this.finished = true;
    }

    @Override
    public String toString() {
        if (!finished) {
            return "No grade yet in " + course.getName();
        }
        if (course.getGradingType() == Course.GradingType.GRADED) {
            return grade + " in " + course.getName();
        }
        if (passed) {
            return "Passed " + course.getName();
        }
        return "Failed " + course.getName();
    }
}
