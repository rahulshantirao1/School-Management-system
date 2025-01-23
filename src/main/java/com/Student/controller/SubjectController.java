package com.Student.controller;

import com.Student.entity.Subject;
import com.Student.payload.SubjectDto;
import com.Student.service.SubjectService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @PostMapping("/addSubject")
    public ResponseEntity<Subject>addSubject(@RequestBody SubjectDto subjectName)
    {
        Subject subject= subjectService.addSubject(subjectName);

        return new ResponseEntity<>(subject, HttpStatus.CREATED);
    }
    @GetMapping("/getAllSubject")
    public ResponseEntity<List<Subject>>getAllSubject(){
        List<Subject> allSubject = subjectService.getAllSubject();
        return new ResponseEntity<>(allSubject,HttpStatus.OK);
    }
    @PutMapping("/updateSubject/{subjectId}")
    public ResponseEntity<Subject>updateSubject(@PathVariable("subjectId")String subjectId,@RequestBody SubjectDto subjectDto){
        Subject subject = subjectService.updateSubject(subjectId,subjectDto);
        return new ResponseEntity<>(subject,HttpStatus.OK);
    }
    @DeleteMapping("/deleteSubject/{subjectId}")
    public ResponseEntity<String>deleteSubject(@PathVariable("subjectId")String subjectId)
    {
        subjectService.deleteSubject(subjectId);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }
}
