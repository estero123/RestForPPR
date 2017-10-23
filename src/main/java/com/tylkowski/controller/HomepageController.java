package com.tylkowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {

    @RequestMapping("/")
    public String showHomepage() {
        System.out.println("showHomepage() controller");
        return "homepage";
    }
}
