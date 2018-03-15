package com.tylkowski.controller;

import com.tylkowski.entity.Group;
import com.tylkowski.entity.Student;
import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
import com.tylkowski.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class StudentRestController {


    private StudentService studentService;


    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    public StudentRestController() {
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<Iterable<Student>> studentList() {
        Iterable<Student> allStudents = studentService.findAll();
        for (Student student : allStudents) {
            Link selfLink = linkTo(StudentRestController.class).slash(student.getSid()).withSelfRel();
            Link groupsLink = linkTo(methodOn(StudentRestController.class).studentGroups(student.getSid())).withRel("groups");
            student.add(selfLink);
            student.add(groupsLink);
        }
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Optional> showStudent(@PathVariable long studentId) {
        Optional<Student> student = studentService.findOne(studentId);
        System.out.println(student.toString());
        if (student.isPresent()) {
            Link selfLink = linkTo(StudentRestController.class).slash(studentId).withSelfRel();
            Link groupsLink = linkTo(methodOn(StudentRestController.class).studentGroups(studentId)).withRel("groups");
            student.get().add(selfLink);
            student.get().add(groupsLink);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{studentId}/groups")
    public ResponseEntity<Iterable<Group>> studentGroups(@PathVariable long studentId) {
        Optional<Student> student = studentService.findOne(studentId);
        if (student.isPresent()) {
            Iterable<Group> studentGroups = student.get().getGroups();
            for (Group group : studentGroups) {
                Link selfLink = linkTo(GroupRestController.class).slash(group.getGid()).withSelfRel();
                group.add(selfLink);
            }
            return new ResponseEntity<>(studentGroups, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    {
//        "firstName": "Name",
//            "lastName": "Surname",
//            "groups": ["http://localhost:8080/api/groups/1","http://localhost:8080/api/groups/2"]
//    }

    @PostMapping(value = {"/", ""})
    public ResponseEntity<Long> addStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
        System.out.println(student.toString());
        if (student.getFirstName().equals("") || student.getLastName().equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        student = studentService.save(student);
        HttpHeaders headers = new HttpHeaders();
        headers.set("sid", String.valueOf(student.getSid()));
        headers.setLocation(ucBuilder.path("/api/students/{sid}").buildAndExpand(student.getSid()).toUri());
        headers.set("Access-Control-Expose-Headers", "Date, location, sid");
        return new ResponseEntity<>(student.getSid(), headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{studentId}/delete/")
    public ResponseEntity<Student> deleteStudent(@PathVariable long studentId){
        Optional<Student> student = studentService.findOne(studentId);
        if (!student.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            studentService.deleteById(studentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @PutMapping(value = {"/", ""})
    public ResponseEntity<Void> updateStudent(@RequestBody Student student) {
        if (studentService.update(student) == Constants.OK) {
            studentService.save(student);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
