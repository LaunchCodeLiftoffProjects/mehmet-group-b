package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.StateRepository;
import com.UberMassage.UberMassage.data.TherapistRepository;
import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.Appointment;
import com.UberMassage.UberMassage.models.State;
import com.UberMassage.UberMassage.models.Therapist;
import com.UberMassage.UberMassage.models.User;
import com.UberMassage.UberMassage.models.dto.LoginFormDTO;
import com.UberMassage.UberMassage.models.dto.RegisterFormDTO;
import com.UberMassage.UberMassage.models.dto.TherapistRegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TherapistRepository therapistRepository;

    @Autowired
    StateRepository stateRepository;

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

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute(new User());
//        model.addAttribute("states",stateCity.getStates().keySet());
//        model.addAttribute("cities",stateCity.getStates().values());



        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }


        User newUser = new User(registerFormDTO.getUsername(),
                registerFormDTO.getPassword(), registerFormDTO.getFirstName()
                , registerFormDTO.getLastName(), registerFormDTO.getState(),
                registerFormDTO.getCity(), registerFormDTO.getEmail(),
                registerFormDTO.getPhoneNumber());


        newUser.setTest(1);
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);

        System.out.println(theUser.getId());

        return "redirect:profile/" + theUser.getId() ;

    }




    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
