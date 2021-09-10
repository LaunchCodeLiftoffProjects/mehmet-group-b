package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.User;
import com.UberMassage.UberMassage.models.dto.LoginFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/{userId}")
    public String displayProfile(Model model, @PathVariable int userId, HttpServletRequest request) {
        User theUser = getUserFromSession(request.getSession());
        model.addAttribute("title", "This is profile");
        model.addAttribute("user",theUser);

        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);
            return "profile/index";

        } else {
            return "redirect:../";
        }

    }

    @GetMapping("")
    public String anotherOne(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                             Errors errors, HttpServletRequest request,
                             Model model) {

        User theUser = getUserFromSession(request.getSession());
        model.addAttribute("user",theUser);

        return "redirect:profile/" + theUser.getId() ;

    }

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

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

}
