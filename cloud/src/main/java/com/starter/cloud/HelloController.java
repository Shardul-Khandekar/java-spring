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
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDTO userDto) {
        
        if( userDto.getAge() < 0){
            return ResponseEntity
                .badRequest()
                .body(new UserResponse("Invalid age", "FAIL"));
        }

        String response = helloService.processUser(userDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new UserResponse(response, "SUCCESS"));
    }

}

// RestController tells spring that this class will handle web requests
// GetMapping tells spring that this method will be called when someone visits localhost:8080/hello
// @Autowired finds the HelloService class and creates an instance, dependency injection
// @PathVariable grabs the value from URL placeholder and passes it into String name variable
// @PostMapping tells spring that this method will respond to POST requests
// @RequestBody tells spring to take incoming JSON and turn into User object
// ResponseEntity allows to control status code, header and body of the response
// Data Transfer Object (DTO) -> Carry data from the outside API to internal logic
// If user object has a password hash, and if entire user object is returned then it carries a risk
// Instead use UserDTO for the outside and UserEntity for internal logic