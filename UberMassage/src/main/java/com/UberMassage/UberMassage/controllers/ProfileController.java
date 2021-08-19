package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    UserRepository userRepository;
    

    @GetMapping("/{userId}")
    public String displayProfile(Model model, @PathVariable int userId) {
        model.addAttribute("title", "This is profile");

        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);

        } else {
            return "redirect:../";
        }



        return "profile/index";
    }

}
