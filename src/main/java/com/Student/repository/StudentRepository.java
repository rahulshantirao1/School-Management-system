package com.Student.repository;

import com.Student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
  @Query("select s from Student s where s.studentUserName=:studentUserName ")
  Optional<Student>findByStudentUserName(@Param("studentUserName") String studentUserName);

  @Query("select s from Student s where s.studentEmail=:studentEmail")
  Optional<Student>findByStudentEmail(@Param("studentEmail") String studentEmail);
}