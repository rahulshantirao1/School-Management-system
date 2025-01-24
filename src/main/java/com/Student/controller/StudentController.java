package com.Student.controller;

import com.Student.entity.Student;
import com.Student.payload.StudentDto;
import com.Student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Student>addAllStudent(@RequestBody StudentDto studentDto)
    {
        Student student = studentService.addStudent(studentDto);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
    @GetMapping("/getStudents")
    public ResponseEntity< List<Student>>getAllStudent()
    {
        List<Student> allStudent = studentService.getAllStudent();
        return new ResponseEntity<>(allStudent,HttpStatus.OK);
    }
    @GetMapping("/getClassStudent")
    public ResponseEntity<List<Student>>getStudentByClassId(@RequestParam("classId")String classId){
        List<Student> students = studentService.getClassByClassId(classId);
        return new ResponseEntity<>(students,HttpStatus.OK);
    }
    @PutMapping("/updateStudent")
    public ResponseEntity<Student>updateStudent(@RequestParam("studentId")String studentId,@RequestBody StudentDto studentDto)
    {
        Student student = studentService.updateStudent(studentId, studentDto);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }
    @PutMapping("/updateStudentClass")
    public ResponseEntity<?>updateStudentClass(@RequestParam("studentId")String studentId,StudentDto studentDto)
    {
        Student student = studentService.updateStudentClass(studentId, studentDto);
        if (student!=null){
            return new ResponseEntity<>(student,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Student Updation Failed",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity<String>deleteStudent(@RequestParam("studentId")String studentId){
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>("Student Deleted Successfully",HttpStatus.OK);
    }
}
