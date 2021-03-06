package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.AppointmentRepository;
import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.Appointment;
import com.UberMassage.UberMassage.models.User;
import com.UberMassage.UberMassage.models.dto.LoginFormDTO;
import com.UberMassage.UberMassage.models.dto.RegisterFormDTO;
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
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping("/{userId}")
    public String displayProfile(Model model, @PathVariable int userId, HttpServletRequest request) {
        User theUser = getUserFromSession(request.getSession());
        model.addAttribute("title", "Welcome " + theUser.getFirstName());
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

    @PostMapping("/{userId}")
    public String deleteAppointment(HttpServletRequest request,
                                    @RequestParam(required = false) int appointmentId, Model model) {
        User theUser = getUserFromSession(request.getSession());

        model.addAttribute("user", theUser);

        Appointment deleteAppointment =
                appointmentRepository.findById(appointmentId).orElse(new Appointment());

        deleteAppointment.getClient().setAppointment(null);

        deleteAppointment.getTherapist().getTherapist().deleteAppointment(deleteAppointment);

        appointmentRepository.deleteById(appointmentId);

        return "profile/index";
    }

    @GetMapping("/edit")
    public String displayEditPage(Model model, HttpServletRequest request) {

        User theUser = getUserFromSession(request.getSession());
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute(theUser);

        return "profile/edit";
    }

    @PostMapping("/edit")
    public String handleEditPage(Model model, HttpServletRequest request,
                                 RegisterFormDTO registerFormDTO) {

        User theUser = getUserFromSession(request.getSession());

        if (registerFormDTO.getEmail() != "") {
            theUser.setEmail(registerFormDTO.getEmail());
        }
        if (registerFormDTO.getPhoneNumber() != "") {
            theUser.setPhoneNumber(registerFormDTO.getPhoneNumber());
        }

        userRepository.save(theUser);

        return "redirect:" + theUser.getId();
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
