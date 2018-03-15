package com.tylkowski;

import com.tylkowski.entity.Group;
import com.tylkowski.entity.Student;
import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.LinkedList;
import java.util.List;
@SpringBootApplication
public class RestService extends SpringBootServletInitializer implements CommandLineRunner{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RestService.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RestService.class, args);
    }

    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService;

    @Override
    public void run(String... args) throws Exception {
        Group informatyka = groupService.save(new Group("Informatyka"));
        Group matematyka = groupService.save(new Group("Matematyka"));
        List<Group> groupsToTylkowski = new LinkedList<>();
        groupsToTylkowski.add(informatyka);
        groupsToTylkowski.add(matematyka);
        List<Group> groupsToKowalski = new LinkedList<>();
        groupsToKowalski.add(matematyka);
        Student student = new Student("Hubert", "Tylkowski", groupsToTylkowski);
        studentService.save(student);
        studentService.save(new Student("Adam", "Kowalski", groupsToKowalski));

    }
}
