package de.heimbas.demo.controller;

import de.heimbas.demo.service.Greeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloWorldController {

    @Autowired
    Greeter greeter;

    @GetMapping
    public String hello() {
        return greeter.getGreeting();
    }

}