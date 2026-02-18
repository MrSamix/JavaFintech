package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/login") // GET-method
    public String Login() {
        return "login";
    }

    @GetMapping("/register") // GET-method
    public String Register() {
        return "register";
    }
}
