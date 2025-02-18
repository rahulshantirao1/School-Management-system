package com.Student.service;

import com.Student.entity.ClassStandard;
import com.Student.entity.Subject;
import com.Student.exception.ResourceNotFoundException;
import com.Student.payload.ClassDto;
import com.Student.repository.ClassStandardRepository;
import com.Student.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ClassStandardService {

    private ClassStandardRepository classStandardRepository;

    public ClassStandardService(ClassStandardRepository classStandardRepository, SubjectService subjectService, SubjectRepository subjectRepository) {
        this.classStandardRepository = classStandardRepository;
        this.subjectService = subjectService;
        this.subjectRepository = subjectRepository;
    }
    private SubjectService subjectService;
private SubjectRepository subjectRepository;
    public ClassStandard addClass(ClassDto classDto) {

        Set<String> subjects = classDto.getSubjects();
        Set<Subject> subjectSet = subjects.stream().map(e -> subjectRepository.findBySubjectName(e).orElseThrow(() -> new ResourceNotFoundException("Please Add Valid Subject: http://localhost:8080/api/v1/subject/addSubject"))).collect(Collectors.toSet());
        List<ClassStandard> class1 = classStandardRepository.findByClassName(classDto.getClassName().toUpperCase()+"-"+classDto.getSection());
        if (class1!=null){
           for (int i=0;i<class1.size();i++){
               Set<Subject> subjects1 = class1.get(i).getSubjects();
               if (subjects1.containsAll(subjectSet)&&subjectSet.containsAll(subjects1)){
                   return class1.get(i);
               }
           }
        }

        String string = UUID.randomUUID().toString();
        String substring = string.substring(1, 4);
        String classId = substring + "-" + classDto.getClassName() +"-"+ string.substring(6, 8);
        ClassStandard cla = new ClassStandard();
        cla.setClassId(classId);
        cla.setStream(classDto.getStream());
         cla.setClassName(classDto.getClassName().toUpperCase()+"-"+classDto.getSection());
         cla.setSubjects(subjectSet);
        ClassStandard save = classStandardRepository.save(cla);
        return save;
    }

    public List<ClassStandard> getAllClass() {

        List<ClassStandard> all = classStandardRepository.findAll();
        return all;
    }

    public ClassStandard updateClass(String classId, ClassDto classDto) {
        ClassStandard class1= classStandardRepository.findById(classId).orElseThrow(() -> new ResourceNotFoundException("Class Not Exist"));

        Set<String> subjects = classDto.getSubjects();
        Set<Subject> subjectSet = subjects.stream().map(e -> subjectRepository.findBySubjectName(e).orElseThrow(() -> new ResourceNotFoundException("Subject Not Exist"))).collect(Collectors.toSet());
        class1.setClassName(classDto.getClassName());
        class1.setSubjects(subjectSet);
        class1.setStream(classDto.getStream());
        ClassStandard save = classStandardRepository.save(class1);
        return save;
    }

    public void deleteClass(String classId) {
        ClassStandard class1= classStandardRepository.findById(classId).orElseThrow(() -> new ResourceNotFoundException("Class Not Exit"));
        Set<Subject> subjects = class1.getSubjects();
        for (Subject s:subjects)
        {
            s.getClassStandards().remove(class1);
            subjectRepository.save(s);
        }
        classStandardRepository.deleteById(classId);
    }

    public ClassStandard findById(String classId) {
        ClassStandard class1= classStandardRepository.findById(classId).orElseThrow(() -> new ResourceNotFoundException("Class Not Exist"));
        return class1;
    }
}
