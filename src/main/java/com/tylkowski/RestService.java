package com.tylkowski;

import com.tylkowski.entity.Group;
import com.tylkowski.entity.Student;
import com.tylkowski.repository.StudentRepository;
import com.tylkowski.service.GroupService;
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

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RestService.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RestService.class, args);
    }
}
