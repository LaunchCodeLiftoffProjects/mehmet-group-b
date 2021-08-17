package com.UberMassage.UberMassage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {

    @GetMapping("")
    public String displayLogin(Model model) {
        model.addAttribute("title", "This is login");

        return "login/index";
    }
}
