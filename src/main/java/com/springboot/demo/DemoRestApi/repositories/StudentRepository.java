package com.springboot.demo.DemoRestApi.repositories;

import com.springboot.demo.DemoRestApi.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends CrudRepository<Student,Long> {

}
