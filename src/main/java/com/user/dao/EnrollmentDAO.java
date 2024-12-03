package com.user.dao;

import com.user.model.Enrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnrollmentDAO {

    private static final Logger LOGGER = Logger.getLogger(EnrollmentDAO.class.getName());

    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Lms";
    private static final String USER = "root";
    private static final String PASS = "root";

    // SQL Queries
    private static final String INSERT_ENROLLMENT = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
    private static final String SELECT_BY_STUDENT_ID = "SELECT * FROM enrollments WHERE student_id = ?";
    private static final String SELECT_BY_COURSE_ID = "SELECT * FROM enrollments WHERE course_id = ?";
    private static final String SELECT_ALL = "SELECT * FROM enrollments";
    private static final String DELETE_ENROLLMENT = "DELETE FROM enrollments WHERE enrollment_id = ?";

    // Database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // Add enrollment
    public void addEnrollment(Enrollment enrollment) {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_ENROLLMENT)) {
            
            pstmt.setInt(1, enrollment.getStudentId());
            pstmt.setInt(2, enrollment.getCourseId());
            pstmt.executeUpdate();
            
            LOGGER.info("Enrollment added successfully.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding enrollment", e);
        }
    }

    // Get enrollments by student ID
    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_STUDENT_ID)) {
            
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    enrollments.add(mapRowToEnrollment(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving enrollments by student ID", e);
        }
        return enrollments;
    }

    // Get enrollments by course ID
    public List<Enrollment> getEnrollmentsByCourseId(int courseId) {
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_COURSE_ID)) {
            
            pstmt.setInt(1, courseId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    enrollments.add(mapRowToEnrollment(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving enrollments by course ID", e);
        }
        return enrollments;
    }

    // Get all enrollments
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL)) {
            
            while (rs.next()) {
                enrollments.add(mapRowToEnrollment(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all enrollments", e);
        }
        return enrollments;
    }

    // Delete enrollment by ID
    public void deleteEnrollment(int enrollmentId) {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_ENROLLMENT)) {
            
            pstmt.setInt(1, enrollmentId);
            int rowsDeleted = pstmt.executeUpdate();
            
            if (rowsDeleted > 0) {
                LOGGER.info("Enrollment deleted successfully.");
            } else {
                LOGGER.warning("No enrollment found with the provided ID.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting enrollment", e);
        }
    }

    // Map ResultSet to Enrollment object
    private Enrollment mapRowToEnrollment(ResultSet rs) throws SQLException {
        return new Enrollment(
                rs.getInt("enrollment_id"),
                rs.getInt("student_id"),
                rs.getInt("course_id"),
                rs.getString("enrolled_at")
        );
    }
}
