package ee.taltech.iti0202.university.declaration.strategy;

import ee.taltech.iti0202.university.course.Course;

import java.util.List;

public interface DeclarationStrategy {

    /**
     * Find preferred courses list.
     *
     * @param minimumCreditPoints for university where strategy operates
     * @return the list of courses that were chosen
     */
    List<Course> findPreferredCourses(int minimumCreditPoints);

    /**
     * Find final courses list.
     *
     * @param minimumCreditPoints for university where strategy operates
     * @param maximumCreditPoints for university where strategy operates
     * @return the list of courses that were chosen
     */
    List<Course> findFinalCourses(int minimumCreditPoints, int maximumCreditPoints);
}
