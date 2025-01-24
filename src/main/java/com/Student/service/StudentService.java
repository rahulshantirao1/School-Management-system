package com.Student.service;

import com.Student.entity.ClassStandard;
import com.Student.entity.MarkSheet;
import com.Student.entity.Student;
import com.Student.entity.Subject;
import com.Student.payload.StudentDto;
import com.Student.repository.ClassStandardRepository;
import com.Student.repository.MarkSheetRepository;
import com.Student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    private StudentRepository studentRepository;
private ClassStandardRepository classStandardRepository;


    public StudentService(StudentRepository studentRepository, ClassStandardRepository classStandardRepository, MarkSheetRepository markSheetRepository) {
        this.studentRepository = studentRepository;
        this.classStandardRepository = classStandardRepository;
        this.markSheetRepository = markSheetRepository;
    }


    public Student addStudent(StudentDto studentDto) {
        Optional<Student> studentEmail =
                studentRepository.findByStudentEmail(studentDto.getStudentEmail());
        if (studentEmail.isPresent())
        {
            throw new RuntimeException("Student Already Present");
        }
        Optional<Student> studentUserName = studentRepository.findByStudentUserName(studentDto.getStudentUserName());
        if (studentUserName.isPresent())
        {
            throw new RuntimeException("Student UserName Already Present");
        }
        Optional<ClassStandard> studentClass = classStandardRepository.findByClassNameAndStream(studentDto.getClassName() + "-" + studentDto.getSection(), studentDto.getStream());

        System.out.println(studentDto.getClassName()+"-"+studentDto.getSection());
       if (studentClass.isPresent())
       {
           String IID = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
           Student student=new Student();
           student.setStudentName(studentDto.getStudentName());
           student.setStudentUserName(studentDto.getStudentUserName());
           student.setStudentId(IID+studentDto.getStudentName().toUpperCase());
           student.setStudentAddress(studentDto.getStudentAddress());
           student.setStudentEmail(studentDto.getStudentEmail());
           student.setStudentParent(studentDto.getStudentParent());
           student.setStudentUserName(studentDto.getStudentUserName());
           student.setDate_Of_Birth(studentDto.getDateOfBirth());
           student.setClassStandard(studentClass.get());
           Student save = studentRepository.save(student);
           return save;

       }else {
           throw new RuntimeException("Class and Stream Not Exist");
       }

         }


    public List<Student> getAllStudent() {
        List<Student> all = studentRepository.findAll();
        return all;
    }

    public List<Student> getClassByClassId(String classId) {
        List<Student> studentByClassId = studentRepository.findStudentByClassId(classId);
        return studentByClassId;
    }

    public Student updateStudent(String studentId, StudentDto studentDto) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student Not Exist"));
        student.setStudentUserName(studentDto.getStudentUserName());
        student.setStudentEmail(studentDto.getStudentEmail());
        Student save = studentRepository.save(student);
        return save;
    }
private MarkSheetRepository markSheetRepository;
    public Student updateStudentClass(String studentId, StudentDto studentDto) {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student Not Exist"));
        List<MarkSheet> allMarkSheet = markSheetRepository.findAllMarkSheet(student.getStudentId(), student.getClassStandard().getClassId());

        for(MarkSheet markSheet:allMarkSheet)
        {
            if (markSheet.getMarksObtained()/markSheet.getTotalMarks()<0.33){
                throw new RuntimeException("You are Failed in "+markSheet.getStudent());
            }
        }

        Optional<ClassStandard> classStandard = classStandardRepository.findByClassNameAndStream(studentDto.getClassName()+"-"+studentDto.getSection(),studentDto.getStream());

        if (classStandard.isPresent())
        {
            student.setClassStandard(classStandard.get());
            Student save = studentRepository.save(student);
            return save;
        }else {
           throw  new RuntimeException("Class Does Not Exist");
        }
    }

    public void deleteStudent(String studentId) {

        studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student Not Exist"));
        studentRepository.deleteById(studentId);
    }
}
