package com.codeup;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "userName") String userName, @RequestParam(name = "password") String password, Model model) {
        System.out.println(userName);
        return "redirect:/posts";
    }

    @PostMapping("/logout")
    public String logout() {
        return "users/login";
    }
}
