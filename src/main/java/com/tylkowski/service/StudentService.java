package com.tylkowski.service;

import com.tylkowski.entity.Student;
import com.tylkowski.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;


public interface StudentService {
    Student save(Student student);
    void save(List<Student> studentList);
    Iterable<Student> findAll();
    Page<Student> findAll(Pageable pageable);
    Student findOne(long studentId);
    void delete(long studentId);
    long count();
}
