package com.tylkowski.controller;

import com.tylkowski.entity.Group;
import com.tylkowski.entity.Student;
import com.tylkowski.repository.StudentRepository;
import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
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

    @PostMapping(value = {"/add-student", "/add-student/"})
    public String addStudentSubmit(@ModelAttribute Student student, Model model) {
        studentService.save(student);
        System.out.println("Success!" + student.getFirstName() + " " + student.getLastName() + " added!");
        model.addAttribute("studentsAmount", studentService.count());
        model.addAttribute("student", new Student());
        model.addAttribute("groupList", groupService.findAll());
        return "addStudent";
    }

    @GetMapping(value = {"/add-group", "/add-group/"})
    public String addGroupPage(Model model) {
        model.addAttribute("groupsAmount", groupService.count());
        model.addAttribute("group", new Group());
        return "addGroup";
    }

    @PostMapping(value = {"/add-group", "/add-group/"})
    public String addGroupSubmit(@ModelAttribute Group group, Model model) {
        groupService.save(group);

        System.out.println("Succees!" + group.getGroupName() + " added!");
        model.addAttribute("groupsAmount", groupService.count());
        model.addAttribute("group", new Group());
        return "addGroup";
    }



//    MODIFY

    @GetMapping(value = {"/modify-group", "/modify-group/"})
    public String modifyGroupPage(Model model) {
        model.addAttribute("groupList", groupService.findAll());
        return "modifyGroup";
    }

    @GetMapping(value = {"/modify-student", "/modify-student/"})
    public String modifyStudentPage(Model model) {
        model.addAttribute("studentList", studentService.findAll());
        return "modifyStudent";
    }


//    DELETE GROUPS/STUDENTS

    @GetMapping(value = {"/modify-group/delete/{groupId}"})
    public String deleteGroupPage(Model model, @PathVariable long groupId) {
        Group group = groupService.findOne(groupId);
        for (Student student :
                group.getStudents()) {
            student.getGroups().remove(group);
        }
        groupService.delete(groupId);
        model.addAttribute("groupList", groupService.findAll());
        return "redirect:/modify-group";
    }

    @GetMapping(value = {"/modify-student/delete/{studentId}"})
    public String deleteStudentPage(Model model, @PathVariable long studentId) {
        studentService.delete(studentId);
        model.addAttribute("studentList", studentService.findAll());
        return "redirect:/modify-student";
    }

//  MODIFY GROUPS/STUDENTS

    @GetMapping("/modify-student/modify/{studentId}")
    public String modifyStudent(Model model, @PathVariable long studentId) {
        Student student = studentService.findOne(studentId);
        model.addAttribute("student", student);
        model.addAttribute("groupList", groupService.findAll());
        return "modifyStudentPage";
    }

    @GetMapping("/modify-group/modify/{groupId}")
    public String modifyGroup(Model model, @PathVariable long groupId) {
        Group group = groupService.findOne(groupId);
        model.addAttribute("group", group);
        model.addAttribute("groupsAmount", groupService.count());
        return "modifyGroupPage";
    }


}
