package com.springboot.demo.DemoRestApi.controller;

import com.springboot.demo.DemoRestApi.beans.StudentWrapper;
import com.springboot.demo.DemoRestApi.exceptions.RecordNotFound;
import com.springboot.demo.DemoRestApi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("/saveStudent")
    public ResponseEntity<StudentWrapper> saveStudent(@RequestBody StudentWrapper studentWrapper){
        studentWrapper=service.saveStudent(studentWrapper);
        return ResponseEntity.ok(studentWrapper);
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<StudentWrapper> getStudentById(@PathVariable Long id) throws RecordNotFound {
       StudentWrapper studentWrapper= service.getStudentById(id);
       return ResponseEntity.ok(studentWrapper);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<StudentWrapper> updateStudent(@RequestBody StudentWrapper studentWrapper) throws RecordNotFound {
        studentWrapper=service.updateStudentDetails(studentWrapper);
        return ResponseEntity.ok(studentWrapper);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<StudentWrapper> deleteStudentById(@PathVariable Long id) throws RecordNotFound {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
