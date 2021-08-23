package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.Therapist;
import com.UberMassage.UberMassage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public String displaySchedule(Model model) {


        User newUser = new User();

        model.addAttribute("title", "This is schedule");
        model.addAttribute("users", userRepository.findAll());

        return "schedule/index";
    }
}
