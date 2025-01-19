package com.Student.repository;

import com.Student.entity.ClassStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClassStandardRepository extends JpaRepository<ClassStandard, String> {
    Optional<ClassStandard>findByClassName(String className);
}