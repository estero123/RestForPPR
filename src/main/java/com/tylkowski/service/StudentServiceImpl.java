package com.tylkowski.service;

import com.tylkowski.entity.Group;
import com.tylkowski.entity.Student;
import com.tylkowski.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.tylkowski.utils.Constants.NOT_EXISTS;
import static com.tylkowski.utils.Constants.OK;

@Service
public class StudentServiceImpl implements StudentService {


    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
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
    public Optional<Student> findById(long studentId) {
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
            if (!studentGroups.stream().allMatch(s -> s != null)) return NOT_EXISTS;
            studentRepository.save(student);
            return OK;
        } else {
            System.out.println("Student with sid " + student.getSid() + " doesn't exists.");
            return NOT_EXISTS;
        }
    }

    @Override
    public long count() {
        return studentRepository.count();
    }
}
