/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ooproject;

/**
 *
 * @author machn
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentCRUD extends JFrame {

    // Form components
    private final JTextField nameField;
    private final JTextField idField;
    private final JTextField[] markFields; // 10 fields for marks input
    private JButton submitButton, viewButton, updateButton, deleteButton;

    public StudentCRUD(String operation) {
        setTitle(operation + " Student");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(14, 2)); // 14 rows (name, id, 10 marks, buttons)

        // Form fields for student name and ID
        add(new JLabel("Student Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Student ID:"));
        idField = new JTextField();
        add(idField);

        // Form fields for 10 subjects (marks)
        markFields = new JTextField[10];
        for (int i = 0; i < 10; i++) {
            add(new JLabel("Subject " + (i + 1) + " Marks:"));
            markFields[i] = new JTextField();
            add(markFields[i]);
        }

        // Buttons for the CRUD operations
        switch (operation) {
            case "Add" -> {
                submitButton = new JButton("Add Student");
                submitButton.addActionListener((ActionEvent e) -> {
                    addStudentAction();
                }); add(submitButton);
            }
            case "Update" -> {
                updateButton = new JButton("Update Student");
                updateButton.addActionListener((ActionEvent e) -> {
                    updateStudentAction();
                }); add(updateButton);
            }
            case "Delete" -> {
                deleteButton = new JButton("Delete Student");
                deleteButton.addActionListener((ActionEvent e) -> {
                    deleteStudentAction();
                }); add(deleteButton);
            }
            case "View" -> {
                viewButton = new JButton("View Students");
                viewButton.addActionListener((ActionEvent e) -> {
                    viewStudentsAction();
                }); add(viewButton);
            }
            default -> {
            }
        }
    }

    // Action for adding a student
    private void addStudentAction() {
        if (validateInput()) {
            String name = nameField.getText();
            int[] marks = new int[10];
            for (int i = 0; i < 10; i++) {
                marks[i] = Integer.parseInt(markFields[i].getText());
            }
            int total = calculateTotal(marks);
            double average = total / 10.0;
            String grade = calculateGrade(average);

            DatabaseManager.addStudent(name, marks, total, average, grade);
            JOptionPane.showMessageDialog(this, "Student added successfully!");
        }
    }

    // Action for updating a student
    private void updateStudentAction() {
        if (validateInput()) {
            String name = nameField.getText();
            int studentId = Integer.parseInt(idField.getText());
            int[] marks = new int[10];
            for (int i = 0; i < 10; i++) {
                marks[i] = Integer.parseInt(markFields[i].getText());
            }
            int total = calculateTotal(marks);
            double average = total / 10.0;
            String grade = calculateGrade(average);

            DatabaseManager.updateStudent(studentId, name, marks, total, average, grade);
            JOptionPane.showMessageDialog(this, "Student updated successfully!");
        }
    }

    // Action for deleting a student
    private void deleteStudentAction() {
        if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the Student ID to delete.");
        } else {
            int studentId = Integer.parseInt(idField.getText());
            DatabaseManager.deleteStudent(studentId);
            JOptionPane.showMessageDialog(this, "Student deleted successfully!");
        }
    }

    // Action for viewing students
    private void viewStudentsAction() {
        ArrayList<Student> students = DatabaseManager.getStudentList();
        StringBuilder studentList = new StringBuilder("Student List:\n");
        for (Student student : students) {
            studentList.append(student.toString()).append("\n");
        }
        JTextArea textArea = new JTextArea(studentList.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Students", JOptionPane.INFORMATION_MESSAGE);
    }

    // Validate form input
    private boolean validateInput() {
        if (nameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student name is required.");
            return false;
        }
        if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student ID is required.");
            return false;
        }
        for (JTextField field : markFields) {
            if (field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "All subject marks are required.");
                return false;
            }
            try {
                int mark = Integer.parseInt(field.getText());
                if (mark < 0 || mark > 100) {
                    JOptionPane.showMessageDialog(this, "Marks should be between 0 and 100.");
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Marks should be valid numbers.");
                return false;
            }
        }
        return true;
    }

    // Helper method to calculate total marks
    private int calculateTotal(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    // Helper method to calculate grade
    private String calculateGrade(double average) {
        if (average >= 70) {
            return "Excellent";
        } else if (average >= 50) {
            return "Good";
        } else {
            return "Poor";
        }
    }

    public static void main(String[] args) {
        StudentCRUD crudForm = new StudentCRUD("Add");
        crudForm.setVisible(true);
    }
}


