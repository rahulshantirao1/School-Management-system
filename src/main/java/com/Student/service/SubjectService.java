package com.Student.service;

import com.Student.entity.ClassStandard;
import com.Student.entity.Student;
import com.Student.entity.Subject;
import com.Student.exception.ResourceNotFoundException;
import com.Student.payload.SubjectDto;
import com.Student.repository.ClassStandardRepository;
import com.Student.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository, ClassStandardRepository classStandardRepository) {
        this.subjectRepository = subjectRepository;
        this.classStandardRepository = classStandardRepository;
    }

    public Subject addSubject(SubjectDto subjectDto) {
        Optional<Subject> subjectAva = subjectRepository.findBySubjectName(subjectDto.getSubjectName().toUpperCase());
        if (subjectAva.isEmpty()){
            String s = UUID.randomUUID().toString();
            String subId = s.substring(0,4) + "-" + subjectDto.getSubjectName().substring(0,3);
            Subject subject = new Subject();
            subject.setSubjectName(subjectDto.getSubjectName().toUpperCase());
            subject.setId(subId.toUpperCase());
            Subject save = subjectRepository.save(subject);
            return save;
        }
        return subjectAva.get();
    }

    public List<Subject> getAllSubject() {

        List<Subject> all = subjectRepository.findAll();
        return all;
    }

    public Subject updateSubject(String subjectId,SubjectDto subjectDto) {

        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Subject Not Available"));
       subject.setSubjectName(subjectDto.getSubjectName().toUpperCase());
        Subject save = subjectRepository.save(subject);
        return save;
    }
private ClassStandardRepository classStandardRepository;
    public void deleteSubject(String subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Subject Not Available"));
        Set<ClassStandard> classStandards = subject.getClassStandards();
        for (ClassStandard c:classStandards)
             {
                       c.getSubjects().remove(subject);
                       classStandardRepository.save(c);
               }
        subjectRepository.deleteById(subjectId);

    }



}
