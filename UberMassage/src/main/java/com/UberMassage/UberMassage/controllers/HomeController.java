package com.UberMassage.UberMassage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("")
    public String displayHomepage(Model model) {
        model.addAttribute("title", "Welcome Gangsters");
        return "index";
    }
}
