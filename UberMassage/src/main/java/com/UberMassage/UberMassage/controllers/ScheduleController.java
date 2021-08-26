package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.Therapist;
import com.UberMassage.UberMassage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    @GetMapping("")
    public String displaySchedule(Model model, HttpServletRequest request,
                                  HttpSession session) {


        User theUser = getUserFromSession(request.getSession());

        model.addAttribute("title", "This is schedule");
        model.addAttribute("users", userRepository.findAll());


        System.out.println(theUser.getTest());

        return "schedule/index";
    }

    @PostMapping("")
    public String handleButton(HttpServletRequest request, Model model,
                               @RequestParam String action) {
        User theUser = getUserFromSession(request.getSession());
        model.addAttribute("title", "This is schedule");
        model.addAttribute("users", userRepository.findAll());

        System.out.println(action);

        if(action.equals("test")) {
            theUser.lucas();
        }

        System.out.println(theUser.getTest());

        return "schedule/index";
    }
}
