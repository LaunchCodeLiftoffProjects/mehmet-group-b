package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.TherapistRepository;
import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.Therapist;
import com.UberMassage.UberMassage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
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

    @Autowired
    TherapistRepository therapistRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public String displaySchedule(Model model) {
        model.addAttribute("title", "This is schedule");

//        loops through users to see if they're therapists, then adds them to the model
       Iterable<User> therapists;
       Iterable<User> users;
       ArrayList<User> usersWhoAreTherapists = new ArrayList<>();
       users = userRepository.findAll();

        for (User user:users
             ) {
        if(user.getTherapist() != null)
            usersWhoAreTherapists.add(user);
        }
        therapists = usersWhoAreTherapists;
//       therapists = therapistRepository.findAll();
       model.addAttribute("therapists",therapists);
//---------

        return "schedule/index";
    }

//    @PostMapping("")
//    public String makeAppointment(Model model){
//      return "redirect"
//    }
}
