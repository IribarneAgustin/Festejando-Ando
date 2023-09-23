package com.microservice.festejandoando.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "${ALLOWED_ORIGINS}")
@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "Hello world";
        
    }

    @GetMapping("home")
    public String home(){
        return "logged succesfully";
        
    }

    @GetMapping("/logout")
    public String logout(){
        return "/";
    }
    
}
