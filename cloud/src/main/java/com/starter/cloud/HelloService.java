package com.starter.cloud;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String getGreeting(String name){
        return "Hello " + name + " from the service layer!";
    }
    
}
