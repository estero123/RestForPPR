package com.tylkowski.controller;

import com.tylkowski.entity.Group;
import com.tylkowski.entity.Student;
import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class GroupRestController {

    private final StudentService studentService;

    private final GroupService groupService;

    @Autowired
    public GroupRestController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<Iterable<Group>> groupList() {
        Iterable<Group> allGroups = groupService.findAll();
        for (Group group : allGroups) {
            Link selfLink = linkTo(GroupRestController.class).slash(group.getGid()).withSelfRel();
            Link studentsLink = linkTo(methodOn(GroupRestController.class).groupStudents(group.getGid())).withRel("students");
            group.add(selfLink);
            group.add(studentsLink);
        }
        return new ResponseEntity<>(allGroups, HttpStatus.OK);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<Optional> showGroup(@PathVariable long groupId) {
        Optional<Group> group = groupService.findOne(groupId);
        if (group.isPresent()) {
            Link selfLink = linkTo(GroupRestController.class).slash(groupId).withSelfRel();
            Link studentsLink = linkTo(methodOn(GroupRestController.class).groupStudents(groupId)).withRel("students");
            group.get().add(selfLink);
            group.get().add(studentsLink);
            return new ResponseEntity<>(group, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{groupId}/students")
    public ResponseEntity<Iterable<Student>> groupStudents(@PathVariable long groupId) {
        Optional<Group> group = groupService.findOne(groupId);
        if (group.isPresent()) {
            List<Student> groupStudents = group.get().getStudents();
            for (Student student : groupStudents) {
                Link linkToStudent = linkTo(StudentRestController.class).slash(student.getSid()).withSelfRel();
                student.add(linkToStudent);
            }
            return new ResponseEntity<>(groupStudents, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = {"/", ""})
    public ResponseEntity<Void> addGroup(@RequestBody Group group, UriComponentsBuilder ucBuilder) {
        if (group.getGroupName().equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        group = groupService.save(group);
        HttpHeaders headers = new HttpHeaders();
        headers.set("groupId", String.valueOf(group.getGid()));
        headers.setLocation(ucBuilder.path("api/groups/{groupId}").buildAndExpand(group.getGid()).toUri());
        headers.set("Access-Control-Expose-Headers", "Date, location, groupId");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @DeleteMapping("/{groupId}/delete/")
    public ResponseEntity<Void> deleteGroup(@PathVariable long groupId) {
        Optional<Group> group = groupService.findOne(groupId);
        if (!group.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            group.get().getStudents().forEach(s -> {
                s.getGroups().remove(group.get());
                studentService.save(s);
            });
//            List<Student> studentList = group.get().getStudents();
//            for (Student student : studentList) {
//                student.getGroups().remove(group.get());
//            }
//            studentService.save(studentList);
//            groupService.save(group.get());
//            for (Student student : group.get().getStudents()) {
//                student.getGroups().remove(group.get());
//
//            }
            groupService.delete(group.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = {"/", ""})
    public ResponseEntity<Void> updateGroup(@RequestBody Group group) {
        if (group != null) {
            groupService.save(group);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
