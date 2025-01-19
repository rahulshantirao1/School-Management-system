package com.Student.repository;

import com.Student.entity.ClassStandard;
import com.Student.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, String> {


    Optional<Subject> findBySubjectName(String subjectName);


}