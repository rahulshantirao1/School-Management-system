package com.Student.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Marks")
public class MarkSheet {
    @Id
    private String markSheetId;
    @Column(name = "marksObtained",nullable = false)
   private Double marksObtained;
    @Column(name = "totalMarks",nullable = false)
   private Double totalMarks;
    @Column(name = "grade",nullable = false)
   private String grade;
    @Column(name = "remarks",nullable = false)
   private String remarks;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "classStandard_id")
    private ClassStandard classStandard;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
