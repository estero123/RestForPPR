package com.tylkowski.controller;

import com.tylkowski.repository.StudentRepository;
import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private GroupService groupService;

    @RequestMapping("/dodaj-studenta")
    public String addStudentPage(Model model) {
        model.addAttribute("studentsAmount", studentService.count());
        return "addStudent";
    }

    @RequestMapping("/dodaj-grupe")
    public String addGroupPage(Model model) {
        model.addAttribute("groupsAmount", groupService.count());
        return "addGroup";
    }
}
