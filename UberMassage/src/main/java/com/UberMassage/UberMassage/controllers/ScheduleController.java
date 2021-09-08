package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.AppointmentRepository;
import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.Appointment;
import com.UberMassage.UberMassage.models.Therapist;
import com.UberMassage.UberMassage.models.User;
import com.UberMassage.UberMassage.models.dto.AppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

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

    @GetMapping("")
    public String displaySchedule(Model model, HttpServletRequest request) {


        User theUser = getUserFromSession(request.getSession());

        model.addAttribute("title", "This is schedule");
        model.addAttribute("user", theUser);
        model.addAttribute("therapists", userRepository.findAll());


        return "schedule/index";
    }

    @PostMapping("")
    public String handleButton(HttpServletRequest request,
                               @RequestParam(value="test") int therapistId,
                               Model model) {
        User theUser = getUserFromSession(request.getSession());
        model.addAttribute("title", "This is schedule");
        model.addAttribute("therapists", userRepository.findAll());
        model.addAttribute("user", theUser);

        User therapist = userRepository.findById(therapistId).orElse(new User());

//        Appointment newAppointment = new Appointment(therapist,
//                    theUser);
//
//        theUser.setAppointment(newAppointment);
//        therapist.setAppointment(newAppointment);

//        appointmentRepository.save(newAppointment);
//
//        userRepository.save(theUser);
//        userRepository.save(therapist);

        return "redirect:appointmentform/" + therapist.getId();
    }
}
