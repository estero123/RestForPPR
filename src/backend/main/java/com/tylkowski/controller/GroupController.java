package com.tylkowski.controller;

import com.tylkowski.entity.Group;
import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class GroupController {
//    public final int PAGE_SIZE = 3;
//
//    @Autowired
//    private StudentService studentService;
//
//    @Autowired
//    private GroupService groupService;
//
//
//
//    @GetMapping(value = {"/add-group", "/add-group/"})
//    public String addGroupPage(Model model) {
//        model.addAttribute("groupsAmount", groupService.count());
//        model.addAttribute("group", new Group());
//        return "addGroup";
//    }
//
//    @GetMapping(value = {"/modify-group", "/modify-group/", "/modify-group/{pageNumber}"})
//    public String modifyGroupPage(@PathVariable Optional<Integer> pageNumber, Model model) {
//        if (!pageNumber.isPresent() || pageNumber.get()<0) pageNumber = Optional.of(0);
//
//        Page<Group> groupList = getGroupPage(pageNumber.get());
//
//        model.addAttribute("groupList", groupList);
//        model.addAttribute("currentPage", groupList.getNumber());
//        model.addAttribute("totalPages", groupList.getTotalPages());
//        return "modifyGroup";
//    }
//
//    private Page<Group> getGroupPage(int pageNumber) {
//        Page<Group> groupPage;
//        PageRequest groupPageRequest = new PageRequest(pageNumber, PAGE_SIZE, Sort.Direction.ASC, "groupName");
//        groupPage = groupService.findAll(groupPageRequest);
//        if (pageNumber > groupPage.getTotalPages()) {
//            groupPage = getGroupPage(groupPage.getTotalPages()-1);
//        }
//        return groupPage;
//    }
//
//    @GetMapping("/modify-group/modify/{groupId}")
//    public String modifyGroup(Model model, @PathVariable long groupId) {
//        Optional<Group> group = groupService.findOne(groupId);
//        model.addAttribute("group", group);
//        model.addAttribute("groupsAmount", groupService.count());
//        return "modifyGroupPage";
//    }
}
