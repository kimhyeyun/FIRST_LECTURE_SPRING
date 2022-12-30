package org.example.ch3.gradeCalculator;

import java.util.List;

public class GradeCalculator {
    private final Courses courses;

    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    public double calculateGrade() {
        double multipliedCreditAndCourseGrade = courses.multiplyCreditAndCourseGrade();

        int totalCompletedCredit = courses.calculateTotalCompletedCredit();


        return multipliedCreditAndCourseGrade / totalCompletedCredit;
    }

}
