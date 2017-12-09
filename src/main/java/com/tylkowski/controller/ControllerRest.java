package com.tylkowski.controller;

import com.tylkowski.entity.Group;
import com.tylkowski.entity.Student;
import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api")
public class ControllerRest {

    @Autowired
    StudentService studentService;

    @Autowired
    GroupService groupService;

    @RequestMapping("/students")
    public Iterable<Student> studentList() {
        return studentService.findAll();
    }

    @RequestMapping("/students/{studentId}")
    public Student showStudent(@PathVariable String studentId) {
        return studentService.findOne(Long.parseLong(studentId));
    }

    @RequestMapping("/groups")
    public Iterable<Group> groupList() {
        return groupService.findAll();
    }

    @RequestMapping("/groups/{groupId}")
    public Group showGroup(@PathVariable String groupId) {
        return groupService.findOne(Long.parseLong(groupId));
    }
}
