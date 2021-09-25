package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.AppointmentRepository;
import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.Appointment;
import com.UberMassage.UberMassage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("appointmentform")
public class AppointmentController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

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

            model.addAttribute("title", "Appointment");
            model.addAttribute("user", theUser);

            User therapist = (User) optTherapist.get();
            model.addAttribute("therapist", therapist);

            model.addAttribute(new Appointment());
            return "appointmentform/index";

        } else {
            return "redirect:../";
        }

    }

    @PostMapping("/{therapistId}")
    public String handleAppointmentPage(Model model,
                                        HttpServletRequest request,
                                        @RequestParam int therapistId,
                                        @RequestParam(value="pickedTime") int pickedTime
    ) {

        User theUser = getUserFromSession(request.getSession());

        User therapist = userRepository.findById(therapistId).orElse(new User());

        Appointment newAppointment = new Appointment(therapist,
                theUser, pickedTime);

        theUser.setAppointment(newAppointment);
        therapist.getTherapist().addAppointment(newAppointment);

        appointmentRepository.save(newAppointment);

        userRepository.save(theUser);
        userRepository.save(therapist);

        return "redirect:/profile";
    }


}
