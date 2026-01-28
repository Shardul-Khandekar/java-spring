package com.starter.cloud;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    // Global state, it will be a part of the heap -> shared space for all threads
    private int counter = 0;

    public String getGreeting(String name){
        return "Hello " + name + " from the service layer!";
    }

    public String processUser(UserDTO user){
        
        counter++;
        System.out.println("Processing user number: " + counter);

        // Local state, will be part of private stack memory
        int methodCounter = 0;
        methodCounter++;

        System.out.println("Method called " + methodCounter);

        return "User " + user.getName() + " age " + user.getAge() + " has been registered!";
    }
    
}
