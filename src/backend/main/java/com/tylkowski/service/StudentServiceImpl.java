package com.tylkowski.service;

import com.tylkowski.entity.Group;
import com.tylkowski.entity.Student;
import com.tylkowski.repository.GroupRepository;
import com.tylkowski.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{


    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        System.out.println(student.toString());
        return studentRepository.save(student);
    }

    @Override
    public void save(List<Student> studentList) {
        studentRepository.saveAll(studentList);
    }

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Optional<Student> findOne(long studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public void deleteById(long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public int update(Student student) {
        Optional<Student> studentFromDatabase = studentRepository.findById(student.getSid()); //check if user exists
        if (studentFromDatabase.isPresent()) {
            List<Group> studentGroups = student.getGroups(); //check if groups exists
            for (Group groupFromStudent: studentGroups) {
                if (groupFromStudent == null) {
                    System.out.println("One of groups doesn't exists.");
                    return -1;
                }
            }
            studentRepository.save(student);
            return 0;


        } else {
            System.out.println("Student with sid " + student.getSid() + " doesn't exists.");
            return -1;
        }
    }

    @Override
    public long count() {
        return studentRepository.count();
    }
}
