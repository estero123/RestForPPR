package com.tylkowski;

import com.tylkowski.entity.Student;
import com.tylkowski.repository.StudentRepository;
import com.tylkowski.service.StudentService;
import com.tylkowski.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class RestService extends SpringBootServletInitializer{

    @Autowired
    StudentService studentService;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RestService.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RestService.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        List<Student> studentList = new LinkedList<>();
//        Student student = new Student();
//        student.setFirstName("Hubert");
//        student.setLastName("Tylkowski");
//        studentList.add(student);
//
//        Student student2 = new Student();
//        student2.setFirstName("Karol");
//        student2.setLastName("Teske");
//        studentList.add(student2);
//
//        studentService.save(studentList);
//
//    }
}
