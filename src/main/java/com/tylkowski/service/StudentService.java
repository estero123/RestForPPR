package com.tylkowski.service;

import com.tylkowski.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface StudentService {
    Student save(Student student);

    void save(List<Student> studentList);

    Iterable<Student> findAll();

    Page<Student> findAll(Pageable pageable);

    Optional<Student> findById(long studentId);

    void deleteById(long studentId);

    int update(Student student);

    long count();

}
