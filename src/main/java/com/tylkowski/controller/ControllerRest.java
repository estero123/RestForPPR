package com.tylkowski.controller;

import com.tylkowski.entity.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/rest")
public class ControllerRest {
    private static final String template = "Witam,  %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="Adam") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
