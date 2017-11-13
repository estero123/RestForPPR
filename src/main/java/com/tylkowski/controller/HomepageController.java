package com.tylkowski.controller;

import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {
    @Autowired
    StudentService studentService;

    @Autowired
    GroupService groupService;

    @RequestMapping("/")
    public String showHomepage(Model model) {
        model.addAttribute("studentsAmount", studentService.count());
        model.addAttribute("groupsAmount", groupService.count());
        return "homepage";
    }
}
