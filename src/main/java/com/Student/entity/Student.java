package com.Student.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student")
public class Student {
    @Id
    private String studentId;
    @Column(name = "studentName",nullable = false,length = 400)
    private String studentName;
    @Column(name = "studentUserName",nullable = false,length = 500,unique = true)
    private String studentUserName;
    @Column(name = "studentParent",nullable = false,length = 1000)
    private String studentParent;
    @Column(name = "studentEmail",nullable = false,length = 1000,unique = true)
    private String studentEmail;
    @Column(name = "studentAddress",nullable = false,length = 1000)
    private String studentAddress;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "standard_Id")
    private ClassStandard classStandard;

    @JsonIgnore
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<MarkSheet> markSheets;

}
