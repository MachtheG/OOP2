/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ooproject;

/**
 *
 * @author machn
 */

public class Student {
    private String name;
    private String studentId;
    private int[] marks; // Marks for 10 subjects
    private int total;
    private double average;
    private String grade;

    // Constructor
    public Student(String name, String studentId, int[] marks, int total, double average, String grade) {
        this.name = name;
        this.studentId = studentId;
        this.marks = marks;
        this.total = total;
        this.average = average;
        this.grade = grade;
    }

    // Getter and Setter methods for all attributes

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Method to calculate the total marks
    public void calculateTotal() {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        this.total = sum;
    }

    // Method to calculate the average marks
    public void calculateAverage() {
        this.average = total / 10.0; // Assuming 10 subjects
    }

    // Method to determine the grade based on average marks
    public void calculateGrade() {
        if (average >= 70) {
            this.grade = "Excellent";
        } else if (average >= 50) {
            this.grade = "Good";
        } else {
            this.grade = "Poor";
        }
    }

    // toString method to return formatted details of the student
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Student ID: ").append(studentId).append("\n");
        sb.append("Marks: \n");
        for (int i = 0; i < marks.length; i++) {
            sb.append("Subject ").append(i + 1).append(": ").append(marks[i]).append("\n");
        }
        sb.append("Total: ").append(total).append("\n");
        sb.append("Average: ").append(String.format("%.2f", average)).append("\n");
        sb.append("Grade: ").append(grade).append("\n");
        return sb.toString();
    }
}
