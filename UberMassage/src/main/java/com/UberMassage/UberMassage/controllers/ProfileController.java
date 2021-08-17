package com.UberMassage.UberMassage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("profile")
public class ProfileController {

    @GetMapping("")
    public String displayProfile(Model model) {
        model.addAttribute("title", "This is profile");

        return "profile/index";
    }

}
