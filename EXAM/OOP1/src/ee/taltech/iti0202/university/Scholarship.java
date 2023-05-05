package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.entity.Student;
import ee.taltech.iti0202.university.exceptions.MonetaryAmountNotPositiveException;
import ee.taltech.iti0202.university.exceptions.StudentAmountNotPositiveException;
import ee.taltech.iti0202.university.exceptions.UniversityMissingException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public final class Scholarship {

    private University university;
    private int minimumCreditPoints;
    private double minimumAverageGrade;
    private int monetaryAmount;
    private int studentAmount;
    private HashMap<Integer, Student> students;
    private Integer id;
    private static int count = 0;

    public static class ScholarshipBuilder {
        private University university;
        private int minimumCreditPoints;
        private double minimumAverageGrade;
        private int monetaryAmount;
        private int studentAmount;

        /**
         * Instantiates a new Scholarship builder.
         */
        public ScholarshipBuilder() {
            university = null;
            minimumCreditPoints = 0;
            minimumAverageGrade = 0;
            monetaryAmount = 0;
            studentAmount = 0;
        }

        /**
         * Add university.
         *
         * @param university the university
         * @return the scholarship builder
         */
        public ScholarshipBuilder withUniversity(University university) {
            this.university = university;
            return this;
        }

        /**
         * Add minimum credit point amount limit.
         *
         * @param minimumCreditPoints the minimum credit points
         * @return the scholarship builder
         */
        public ScholarshipBuilder withMinimumCreditPoints(int minimumCreditPoints) {
            this.minimumCreditPoints = minimumCreditPoints;
            return this;
        }

        /**
         * Add minimum average grade limit.
         *
         * @param minimumAverageGrade the minimum average grade
         * @return the scholarship builder
         */
        public ScholarshipBuilder withMinimumAverageGrade(double minimumAverageGrade) {
            this.minimumAverageGrade = minimumAverageGrade;
            return this;
        }

        /**
         * Add monetary amount.
         *
         * @param monetaryAmount the monetary amount
         * @return the scholarship builder
         */
        public ScholarshipBuilder withMonetaryAmount(int monetaryAmount) {
            this.monetaryAmount = monetaryAmount;
            return this;
        }

        /**
         * Add student amount.
         *
         * @param studentAmount the student amount
         * @return the scholarship builder
         */
        public ScholarshipBuilder withStudentAmount(int studentAmount) {
            this.studentAmount = studentAmount;
            return this;
        }

        /**
         * Build scholarship.
         *
         * @return the scholarship
         */
        public Scholarship build() {
            return new Scholarship(
                university,
                minimumCreditPoints,
                minimumAverageGrade,
                monetaryAmount,
                studentAmount
            );
        }
    }

    private Scholarship(
        University university,
        int minimumCreditPoints,
        double minimumAverageGrade,
        int monetaryAmount,
        int studentAmount
    ) {
        if (university == null) {
            throw new UniversityMissingException();
        }
        if (monetaryAmount <= 0) {
            throw new MonetaryAmountNotPositiveException();
        }
        if (studentAmount <= 0) {
            throw new StudentAmountNotPositiveException();
        }
        this.university = university;
        this.minimumCreditPoints = minimumCreditPoints;
        this.minimumAverageGrade = minimumAverageGrade;
        this.monetaryAmount = monetaryAmount;
        this.studentAmount = studentAmount;
        this.university.addScholarship(this);
        this.students = new HashMap<>();
        count += 1;
        this.id = count;
    }

    public Integer getId() {
        return id;
    }

    /**
     * Find students who should get the scholarship.
     */
    public void grant() {
        List<Student> eligibleStudents = university.getStudents().stream()
            .filter(x -> x.getCompletedCreditPoints() >= minimumCreditPoints)
            .filter(x -> x.averageGrade() >= minimumAverageGrade)
            .sorted()
            .collect(Collectors.toList());
        for (int i = 0; i < Math.min(studentAmount, eligibleStudents.size()); i++) {
            Student student = eligibleStudents.get(i);
            students.put(student.getId(), student);
            student.addScholarship(this);
        }
    }

    public int getMonetaryAmount() {
        return monetaryAmount;
    }
}
