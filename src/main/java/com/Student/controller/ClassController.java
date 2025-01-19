package com.Student.controller;

import com.Student.entity.ClassStandard;
import com.Student.payload.ClassDto;
import com.Student.service.ClassStandardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/class")
public class ClassController {

    private ClassStandardService classStandardService;

    public ClassController(ClassStandardService classStandardService) {
        this.classStandardService = classStandardService;
    }

    @PostMapping("/addClass")
    public ResponseEntity<ClassStandard>addClass(@RequestBody ClassDto classDto)
    {
        ClassStandard classStandard = classStandardService.addClass(classDto);
        return new ResponseEntity<>(classStandard, HttpStatus.CREATED);
    }
    @GetMapping("/getAllClass")
    public ResponseEntity<List<ClassStandard>>getAllClass()
    {
        List<ClassStandard> allClass = classStandardService.getAllClass();
        return new ResponseEntity<>(allClass,HttpStatus.OK);
    }

    @PutMapping("/updateClass")
    public ResponseEntity<ClassStandard>updateClass(@RequestParam("classId")String classId,@RequestBody ClassDto classDto)
    {
        classDto.setClassName(classDto.getClassName().toUpperCase());
        ClassStandard classStandard = classStandardService.updateClass(classId, classDto);
        return new ResponseEntity<>(classStandard,HttpStatus.OK);
    }
    @DeleteMapping("/deleteClass/{classId}")
    public ResponseEntity<String>deleteClass(@PathVariable("classId")String classId)
    {
        classStandardService.deleteClass(classId);
        return new ResponseEntity<>("Class Deleted Succussfully",HttpStatus.OK);
    }
    @GetMapping("/getClassByClassId")
    public ResponseEntity<ClassStandard>getClassByClassId(@RequestParam("classId")String classId)
    {
        ClassStandard byId = classStandardService.findById(classId);
        return new ResponseEntity<>(byId,HttpStatus.OK);
    }
}
