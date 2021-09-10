package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("appointmentform")
public class AppointmentController {

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

    @GetMapping("/{therapistId}")
    public String displayAppointmentPage(Model model,
                                         HttpServletRequest request,
                                         @PathVariable int therapistId
                                         ) {
        User theUser = getUserFromSession(request.getSession());

        Optional optTherapist = userRepository.findById(therapistId);
        if (optTherapist.isPresent()) {

            model.addAttribute("title", "Your relaxation is just around the corner");
            model.addAttribute("user", theUser);

            User therapist = (User) optTherapist.get();
            model.addAttribute("therapist", therapist);
            return "appointmentform/index";

        } else {
            return "redirect:../";
        }

    }


}
