package com.tylkowski.controller;

import com.tylkowski.entity.Group;
import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GroupController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService;



    @GetMapping(value = {"/add-group", "/add-group/"})
    public String addGroupPage(Model model) {
        model.addAttribute("groupsAmount", groupService.count());
        model.addAttribute("group", new Group());
        return "addGroup";
    }

    @GetMapping(value = {"/modify-group", "/modify-group/"})
    public String modifyGroupPage(Model model) {
        model.addAttribute("groupList", groupService.findAll());
        return "modifyGroup";
    }

    @GetMapping("/modify-group/modify/{groupId}")
    public String modifyGroup(Model model, @PathVariable long groupId) {
        Group group = groupService.findOne(groupId);
        model.addAttribute("group", group);
        model.addAttribute("groupsAmount", groupService.count());
        return "modifyGroupPage";
    }
}
