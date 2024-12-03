// Source code is decompiled from a .class file using FernFlower decompiler.
package com.user.model;

public class Course {
   private int courseId;
   private String courseName;
   private String description;
   private String createdAt;

   public Course(int courseId, String courseName, String description, String createdAt) {
      this.courseId = courseId;
      this.courseName = courseName;
      this.description = description;
      this.createdAt = createdAt;
   }

   public int getCourseId() {
      return this.courseId;
   }

   public void setCourseId(int courseId) {
      this.courseId = courseId;
   }

   public String getCourseName() {
      return this.courseName;
   }

   public void setCourseName(String courseName) {
      this.courseName = courseName;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getCreatedAt() {
      return this.createdAt;
   }

   public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
   }

   public String toString() {
      return "Course{courseId=" + this.courseId + ", courseName='" + this.courseName + '\'' + ", description='" + this.description + '\'' + ", createdAt='" + this.createdAt + '\'' + '}';
   }
}
