package com.tylkowski.service;

import com.tylkowski.entity.Student;
import com.tylkowski.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {
    void save(Student student);
    void save(List<Student> studentList);
    Iterable<Student> findAll();
}
