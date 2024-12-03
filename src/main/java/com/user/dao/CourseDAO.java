package com.user.dao;

import com.user.model.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Lms";
    private static final String USER = "root";
    private static final String PASS = "root";

    // Helper method to establish connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // Add a new course
    public void addCourse(Course course) {
        String sql = "INSERT INTO courses (course_name, description) VALUES (?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getDescription());
            pstmt.executeUpdate();
            System.out.println("Course added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding course: " + e.getMessage());
        }
    }

    // Retrieve a course by its ID
    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("description"),
                        rs.getString("created_at")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving course: " + e.getMessage());
        }
        return null;
    }

    // Retrieve all courses
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                courses.add(new Course(
                    rs.getInt("course_id"),
                    rs.getString("course_name"),
                    rs.getString("description"),
                    rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving courses: " + e.getMessage());
        }
        return courses;
    }

    // Update an existing course
    public void updateCourse(Course course) {
        String sql = "UPDATE courses SET course_name = ?, description = ? WHERE course_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getDescription());
            pstmt.setInt(3, course.getCourseId());
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Course updated successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating course: " + e.getMessage());
        }
    }

    // Delete a course by its ID
    public void deleteCourse(int courseId) {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Course deleted successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting course: " + e.getMessage());
        }
    }
}
