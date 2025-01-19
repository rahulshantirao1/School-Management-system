package com.Student.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="subject")
public class Subject {

	@Id
	@Column(name = "subjectId",nullable = false)
	private String id;

	@Column(name = "subjectName",nullable = false,length = 255,unique = true)
	private String subjectName;


	@JsonIgnore
     @ManyToMany(mappedBy = "subjects")
	private Set<ClassStandard>classStandards=new HashSet<>();


}
