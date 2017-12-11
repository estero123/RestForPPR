package com.tylkowski.controller;

import com.tylkowski.entity.Group;
import com.tylkowski.entity.Student;
import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class ControllerRest {

    @Autowired
    StudentService studentService;

    @Autowired
    GroupService groupService;

    @GetMapping("/students")
    public ResponseEntity<Iterable<Student>> studentList() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> showStudent(@PathVariable long studentId) {
        Student student = studentService.findOne(studentId);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/students/{studentId}/groups")
    public ResponseEntity<Iterable<Group>> studentGroups(@PathVariable long studentId) {
        Student student = studentService.findOne(studentId);
        if (student != null) {
            return new ResponseEntity<>(student.getGroups(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/students", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
        studentService.save(student);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("api/students/{studentId}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/students/delete/{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long studentId){
        Student student = studentService.findOne(studentId);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            student.getGroups().clear();
            studentService.delete(studentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/students/{studentId}/remove/{groupId}")
    public ResponseEntity<Void> deleteGroupFromStudent(@PathVariable long studentId, @PathVariable long groupId) {
        return deleteStudentFromGroup(studentId, groupId);
    }

    @PutMapping(value = "/students/update/{studentId}")
    public ResponseEntity<Void> updateStudent(@PathVariable long studentId, @RequestBody Student student) {
        Student studentToChange = studentService.findOne(studentId);
        if ( student != null && studentToChange != null) {
            studentService.save(student);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }












    @GetMapping("/groups")
    public ResponseEntity<Iterable<Group>> groupList() {
        return new ResponseEntity<>(groupService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<Group> showGroup(@PathVariable long groupId) {
        Group group = groupService.findOne(groupId);
        if ( group != null) {
            return new ResponseEntity<>(group, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/groups/{groupId}/students")
    public ResponseEntity<Iterable<Student>> groupStudents(@PathVariable long groupId) {
        Group group = groupService.findOne(groupId);
        if ( group != null) {
            return new ResponseEntity<>(group.getStudents(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/groups" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addGroup(@RequestBody Group group, UriComponentsBuilder ucBuilder) {
        groupService.save(group);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("api/groups/{groupId}").buildAndExpand(group.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/groups/delete/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable long groupId){
        Group group = groupService.findOne(groupId);
        if(group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Student student : group.getStudents()) {
                student.getGroups().remove(group);
                studentService.save(student);
            }
            groupService.delete(groupId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/groups/{groupId}/remove/{studentId}")
    public ResponseEntity<Void> deleteStudentFromGroup(@PathVariable long studentId, @PathVariable long groupId) {
        Student student = studentService.findOne(studentId);
        Group group = groupService.findOne(groupId);
        if(student != null && group != null) {
            student.getGroups().remove(group);
            studentService.save(student);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/groups/update/{groupId}")
    public ResponseEntity<Void> updateGroup(@PathVariable long groupId, @RequestBody Group group) {
        Group groupToChange = groupService.findOne(groupId);
        if ( group != null && groupToChange != null) {
            groupService.save(group);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
