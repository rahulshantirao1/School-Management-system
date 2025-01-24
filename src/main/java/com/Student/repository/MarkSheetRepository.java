package com.Student.repository;

import com.Student.entity.MarkSheet;
import com.Student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarkSheetRepository extends JpaRepository<MarkSheet, String> {


    @Query("select m from MarkSheet m JOIN m.student s WHERE s.studentId=:Id OR s.studentUserName=:Id OR s.studentEmail=:Id")
    List<MarkSheet>findMarkSheetByStudentIdOrStudentEmailOrStudentUserName(@Param("Id")String studentId);

    @Query("select m from MarkSheet m JOIN m.classStandard c WHERE c.className=:className")
    List<MarkSheet>findAllClassMarkSheets(@Param("class")String className);

    @Query("select m from MarkSheet m JOIN m.student s JOIN m.classStandard c where s.studentId=:studentId AND c.classId=:classId")
    List<MarkSheet>findAllMarkSheet(@Param("studentId")String studentId,@Param("classId")String classId);
}