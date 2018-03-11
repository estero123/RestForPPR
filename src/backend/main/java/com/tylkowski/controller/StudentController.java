package com.tylkowski.controller;

import com.tylkowski.entity.Student;
import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class StudentController {
//    @Autowired
//    private StudentService studentService;
//
//    @Autowired
//    private GroupService groupService;
//
//    private final int PAGE_SIZE = 3;
//
//    @GetMapping(value = {"/add-student", "/add-student/"})
//    public String addStudentPage(Model model) {
//        model.addAttribute("studentsAmount", studentService.count());
//        model.addAttribute("student", new Student());
//        model.addAttribute("groupList", groupService.findAll());
//        return "addStudent";
//    }
//
//    @GetMapping(value = {"/modify-student", "/modify-student/", "/modify-student/{pageNumber}"})
//    public String modifyStudentPage(@PathVariable Optional<Integer> pageNumber, Model model) {
//        if (!pageNumber.isPresent() || pageNumber.get()<0) pageNumber = Optional.of(0);
//
//        Page<Student> studentPage = getStudentPage(pageNumber.get());
//
//        model.addAttribute("studentList", studentPage);
//        model.addAttribute("currentPage", studentPage.getNumber());
//        model.addAttribute("totalPages", studentPage.getTotalPages());
//        return "modifyStudent";
//    }
//
//    private Page<Student> getStudentPage(int pageNumber) {
//        Page<Student> studentsPage;
//        PageRequest studentPageRequest = new PageRequest(pageNumber, PAGE_SIZE, Sort.Direction.ASC, "lastName");
//        studentsPage = studentService.findAll(studentPageRequest);
//        if (pageNumber > studentsPage.getTotalPages()) {
//            studentsPage = getStudentPage(studentsPage.getTotalPages()-1);
//        }
//        return studentsPage;
//    }
//
//    @GetMapping("/modify-student/modify/{studentId}")
//    public String modifyStudent(Model model, @PathVariable long studentId) {
//        Optional<Student> student = studentService.findOne(studentId);
//        model.addAttribute("student", student);
//        model.addAttribute("groupList", groupService.findAll());
//        return "modifyStudentPage";
//    }
}
