package com.Student.repository;

import com.Student.entity.ClassStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClassStandardRepository extends JpaRepository<ClassStandard, String> {
    List<ClassStandard> findByClassName(String className);
    @Query("select c from ClassStandard c where c.className=:className AND c.stream=:stream")
    Optional<ClassStandard>findByClassNameAndStream(@Param("className")String className,@Param("stream")String stream);
}