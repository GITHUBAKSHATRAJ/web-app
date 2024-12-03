// Source code is decompiled from a .class file using FernFlower decompiler.
package com.user.model;

public class Student {
   private int studentId;
   private String name;
   private String email;
   private String createdAt;

   public Student(int studentId, String name, String email, String createdAt) {
      this.studentId = studentId;
      this.name = name;
      this.email = email;
      this.createdAt = createdAt;
   }

   public int getStudentId() {
      return this.studentId;
   }

   public void setStudentId(int studentId) {
      this.studentId = studentId;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getCreatedAt() {
      return this.createdAt;
   }

   public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
   }

   public String toString() {
      return "Student{studentId=" + this.studentId + ", name='" + this.name + '\'' + ", email='" + this.email + '\'' + ", createdAt='" + this.createdAt + '\'' + '}';
   }
}
