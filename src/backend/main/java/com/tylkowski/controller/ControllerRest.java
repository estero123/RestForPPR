package com.tylkowski.controller;

import com.tylkowski.entity.Group;
import com.tylkowski.entity.Student;
import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ControllerRest {

    @Autowired
    StudentService studentService;

    @Autowired
    GroupService groupService;



//    @DeleteMapping("/students/delete/{studentId}")
//    public ResponseEntity<Student> deleteStudent(@PathVariable long studentId){
//        Student student = studentService.findOne(studentId);
//        if (student == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        else {
//            student.getGroups().clear();
//            studentService.deleteById(studentId);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//    }
//
//    @DeleteMapping("/students/{studentId}/remove/{groupId}")
//    public ResponseEntity<Void> deleteGroupFromStudent(@PathVariable long studentId, @PathVariable long groupId) {
//        return deleteStudentFromGroup(studentId, groupId);
//    }
//
//    @PutMapping(value = "/students/update/")
//    public ResponseEntity<Void> updateStudent(@RequestBody Student student) {
//        if (studentService.update(student) == 0) {
//            studentService.save(student);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

}
