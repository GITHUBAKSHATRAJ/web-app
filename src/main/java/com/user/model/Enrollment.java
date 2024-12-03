// Source code is decompiled from a .class file using FernFlower decompiler.
package com.user.model;

public class Enrollment {
   private int enrollmentId;
   private int studentId;
   private int courseId;
   private String enrolledAt;

   public Enrollment(int enrollmentId, int studentId, int courseId, String enrolledAt) {
      this.enrollmentId = enrollmentId;
      this.studentId = studentId;
      this.courseId = courseId;
      this.enrolledAt = enrolledAt;
   }

   public int getEnrollmentId() {
      return this.enrollmentId;
   }

   public void setEnrollmentId(int enrollmentId) {
      this.enrollmentId = enrollmentId;
   }

   public int getStudentId() {
      return this.studentId;
   }

   public void setStudentId(int studentId) {
      this.studentId = studentId;
   }

   public int getCourseId() {
      return this.courseId;
   }

   public void setCourseId(int courseId) {
      this.courseId = courseId;
   }

   public String getEnrolledAt() {
      return this.enrolledAt;
   }

   public void setEnrolledAt(String enrolledAt) {
      this.enrolledAt = enrolledAt;
   }

   public String toString() {
      return "Enrollment{enrollmentId=" + this.enrollmentId + ", studentId=" + this.studentId + ", courseId=" + this.courseId + ", enrolledAt='" + this.enrolledAt + '\'' + '}';
   }
}
