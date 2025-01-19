package com.Student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "class_standard")
public class ClassStandard {
    @Id
    @Column(name = "classId", nullable = false)
    private String classId;
    @Column(name = "className",length = 200,nullable = false,unique = true)
    private String className;

    @ManyToMany
    @JoinTable(
            name = "classStandard_Subject",
            joinColumns = @JoinColumn(name = "classStandard_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject>subjects=new HashSet<>();
}