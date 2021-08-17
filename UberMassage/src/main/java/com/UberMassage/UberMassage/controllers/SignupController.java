package com.UberMassage.UberMassage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("signup")
public class SignupController {

    @GetMapping("")
    public String displaySignUp(Model model) {
        model.addAttribute("title", "This is signup");

        return "signup/index";
    }

}
