package com.springboot.demo.DemoRestApi.services;

import com.springboot.demo.DemoRestApi.beans.StudentWrapper;
import com.springboot.demo.DemoRestApi.exceptions.RecordNotFound;
import com.springboot.demo.DemoRestApi.model.Student;
import com.springboot.demo.DemoRestApi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    //save a student record
    public StudentWrapper saveStudent(StudentWrapper inputStudent){
        Student student=new Student();
        student.setName(inputStudent.getName());
        student=studentRepository.save(student);
        inputStudent.setId(student.getId());
        inputStudent.setName(student.getName());
        return inputStudent;
    }

    //get a student record by id
    public StudentWrapper getStudentById(Long id) throws RecordNotFound {
        StudentWrapper studentWrapper=null;
        Optional<Student> studentOptional=studentRepository.findById(id);
        if(studentOptional.isPresent()){
            studentWrapper=new StudentWrapper();
            Student student=studentOptional.get();
            studentWrapper.setId(student.getId());
            studentWrapper.setName(student.getName());
        }
        else {
            throw new RecordNotFound("Student record not found");
        }
        return studentWrapper;
    }

    //update student record
    public StudentWrapper updateStudentDetails(StudentWrapper inputStudent) throws RecordNotFound{
        Optional<Student> studentOptionalData=studentRepository.findById(inputStudent.getId());
        if(studentOptionalData.isPresent()){
            Student student=studentOptionalData.get();

            //update the name
            student.setName(inputStudent.getName());
            studentRepository.save(student);

            //populate the updated details in the output object
            StudentWrapper studentWrapper=new StudentWrapper();
            studentWrapper.setId(student.getId());
            studentWrapper.setName(student.getName());
            return studentWrapper;
        }
        else {
            throw new RecordNotFound("Student record not found");
        }
    }
    //delete student record
    public void deleteById(Long id) throws  RecordNotFound {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            Student student=studentOptional.get();
            studentRepository.delete(student);
        }else {
            throw new RecordNotFound("Record not found");
        }
    }
}
