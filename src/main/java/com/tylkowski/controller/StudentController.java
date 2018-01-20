package com.tylkowski.controller;

import com.tylkowski.entity.Student;
import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService;

    @GetMapping(value = {"/add-student", "/add-student/"})
    public String addStudentPage(Model model) {
        model.addAttribute("studentsAmount", studentService.count());
        model.addAttribute("student", new Student());
        model.addAttribute("groupList", groupService.findAll());
        return "addStudent";
    }

    @GetMapping(value = {"/modify-student", "/modify-student/"})
    public String modifyStudentPage(Model model) {
        model.addAttribute("studentList", studentService.findAll());
        return "modifyStudent";
    }

    @GetMapping("/modify-student/modify/{studentId}")
    public String modifyStudent(Model model, @PathVariable long studentId) {
        Student student = studentService.findOne(studentId);
        model.addAttribute("student", student);
        model.addAttribute("groupList", groupService.findAll());
        return "modifyStudentPage";
    }
}
