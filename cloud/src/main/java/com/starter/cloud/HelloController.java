package com.starter.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String sayHello() {
        return helloService.getGreeting();
    }
    
}

// RestController tells spring that this class will handle web requests
// GetMapping tells spring that this method will be called when someone visits localhost:8080/hello
// @Autowired finds the HelloService class and creates an instance, dependency injection