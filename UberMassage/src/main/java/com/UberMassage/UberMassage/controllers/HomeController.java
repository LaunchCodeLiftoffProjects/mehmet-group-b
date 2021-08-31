package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.CityRepository;
import com.UberMassage.UberMassage.data.StateRepository;
import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.City;
import com.UberMassage.UberMassage.models.State;
import com.UberMassage.UberMassage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StateRepository stateRepository;
    @Autowired
    CityRepository cityRepository;

    private static final String userSessionKey = "client";

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
    public String displayHomepage(Model model, HttpServletRequest request) {
        User theUser = getUserFromSession(request.getSession());

        model.addAttribute("title", "Hello World");
        model.addAttribute("client", theUser);



        return "index";
    }
}
