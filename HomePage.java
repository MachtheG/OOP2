package com.mycompany.ooproject; // Ensure package declaration is at the top

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {

    // For MySQL connection
    private static final String URL = "jdbc:mysql://localhost:3306/oop1";
    private static final String USERNAME = "localhost";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // Method to create tables (Admin and Students)
    public static void setupDatabase() {
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();

            // Create Admin table
            String createAdminTable = "CREATE TABLE IF NOT EXISTS Admin (" +
                    "admin_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username VARCHAR(50), " +
                    "password VARCHAR(100))";
            stmt.execute(createAdminTable);

            // Create Students table
            String createStudentTable = "CREATE TABLE IF NOT EXISTS Students (" +
                    "student_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR(100), subject1 INTEGER, subject2 INTEGER, " +
                    "subject3 INTEGER, subject4 INTEGER, subject5 INTEGER, " +
                    "subject6 INTEGER, subject7 INTEGER, subject8 INTEGER, " +
                    "subject9 INTEGER, subject10 INTEGER, total INTEGER, " +
                    "average REAL, grade VARCHAR(10))";
            stmt.execute(createStudentTable);

        } catch (SQLException e) {
        }
    }

    // Method for admin login validation
    public static boolean validateAdmin(String username, String password) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database!");
            }
            String query = "SELECT * FROM Admin WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            // Debugging: Print the username and password
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

            pstmt.setString(1, username.trim());
            pstmt.setString(2, password.trim());
            ResultSet rs = pstmt.executeQuery();

            // Check if a record is found in the result set
            if (rs.next()) {
                System.out.println("Admin found!");
                return true;
            } else {
                System.out.println("Admin not found!");
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    // CRUD operations for Students (Add, Update, Delete, Retrieve)

    // Add a new student
    public static void addStudent(String name, int[] marks, int total, double average, String grade) {
        String query = "INSERT INTO Students (name, subject1, subject2, subject3, subject4, " +
                "subject5, subject6, subject7, subject8, subject9, subject10, total, average, grade) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            for (int i = 0; i < 10; i++) {
                pstmt.setInt(i + 2, marks[i]);
            }
            pstmt.setInt(12, total);
            pstmt.setDouble(13, average);
            pstmt.setString(14, grade);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Update student information
    public static void updateStudent(int studentId, String name, int[] marks, int total, double average, String grade) {
        String query = "UPDATE Students SET name = ?, subject1 = ?, subject2 = ?, subject3 = ?, subject4 = ?, " +
                "subject5 = ?, subject6 = ?, subject7 = ?, subject8 = ?, subject9 = ?, subject10 = ?, " +
                "total = ?, average = ?, grade = ? WHERE student_id = ?";
        try (Connection conn = getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            for (int i = 0; i < 10; i++) {
                pstmt.setInt(i + 2, marks[i]);
            }
            pstmt.setInt(12, total);
            pstmt.setDouble(13, average);
            pstmt.setString(14, grade);
            pstmt.setInt(15, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Delete a student record
    public static void deleteStudent(int studentId) {
        String query = "DELETE FROM Students WHERE student_id = ?";
        try (Connection conn = getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Retrieve all student records
    public static ArrayList<Student> getStudentList() {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Students";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("name");
                int[] marks = new int[10];
                for (int i = 0; i < 10; i++) {
                    marks[i] = rs.getInt("subject" + (i + 1));
                }
                int total = rs.getInt("total");
                double average = rs.getDouble("average");
                String grade = rs.getString("grade");
                students.add(new Student(name, rs.getString("student_id"), marks, total, average, grade));
            }
        } catch (SQLException e) {
        }
        return students;
    }
}
