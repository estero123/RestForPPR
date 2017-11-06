package com.tylkowski.controller;

import com.tylkowski.entity.Greeting;
import com.tylkowski.entity.Student;
import com.tylkowski.repository.StudentRepository;
import com.tylkowski.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/rest")
public class ControllerRest {

    @Autowired
    StudentService studentService;

    private static final String template = "Witam,  %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="Adam") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/students")
    public Iterable<Student> studentList() {
        return studentService.findAll();
    }
}
