package com.Student.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@Column(name = "subjectId",nullable = false)
	private String id;

	@Column(name = "subjectName",nullable = false,length = 255,unique = true)
	private String subjectName;


	@JsonIgnore
     @ManyToMany(mappedBy = "subjects")
	private Set<ClassStandard>classStandards=new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "subject",cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<MarkSheet>markSheets;

@Override
public String toString(){
	return "Id: "+id+"SubjectName: "+subjectName;
}
@Override
public boolean equals(Object object){
	if (object==this){
		return true;
	}
	if (object instanceof Subject)
	{
		Subject sub = (Subject) object;
		if (this.subjectName.equals(sub.getSubjectName()) && this.id==sub.getId()){
			return true;
		}
	}
	return false;
}

@Override
public int hashCode(){

	  return this.id.hashCode();
}

}