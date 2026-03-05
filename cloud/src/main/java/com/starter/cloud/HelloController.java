package com.starter.cloud;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return helloService.getGreeting(name);
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        
        if( user.getAge() < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Age cannot be negative!");
        }
        String response = helloService.processUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(response);
    }

}

// RestController tells spring that this class will handle web requests
// GetMapping tells spring that this method will be called when someone visits localhost:8080/hello
// @Autowired finds the HelloService class and creates an instance, dependency injection
// @PathVariable grabs the value from URL placeholder and passes it into String name variable
// @PostMapping tells spring that this method will respond to POST requests
// @RequestBody tells spring to take incoming JSON and turn into User object
// ResponseEntity allows to control status code, header and body of the response