package com.UberMassage.UberMassage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

    @GetMapping("")
    public String displaySchedule(Model model) {
        model.addAttribute("title", "This is schedule");

        return "schedule/index";
    }
}
