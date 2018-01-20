package com.tylkowski.service;

import com.tylkowski.entity.Student;
import com.tylkowski.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        System.out.println(student.getFirstName() + " " + student.getLastName());
        return studentRepository.save(student);
    }

    @Override
    public void save(List<Student> studentList) {
        studentRepository.save(studentList);
    }

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Iterable<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student findOne(long studentId) {
        return studentRepository.findOne(studentId);
    }

    @Override
    public void delete(long studentId) {
        studentRepository.delete(studentId);
    }

    @Override
    public long count() {
        return studentRepository.count();
    }
}
