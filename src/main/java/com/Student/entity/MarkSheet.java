package com.Student.entity;

import jakarta.persistence.*;
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

    @Column(name = "marks",nullable = false)
    private HashMap<Subject,Integer> marks;

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
