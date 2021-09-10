package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.TherapistRepository;
import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.Gender;
import com.UberMassage.UberMassage.models.Hours;
import com.UberMassage.UberMassage.models.Therapist;
import com.UberMassage.UberMassage.models.User;
import com.UberMassage.UberMassage.models.dto.TherapistRegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("therapistsignup")
public class
ThereapistSignupController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TherapistRepository therapistRepository;

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

    @GetMapping()
    public String displayTherapistSignupForm(Model model,HttpServletRequest request) {
        User newUser = getUserFromSession(request.getSession());

        model.addAttribute("user",newUser);
        model.addAttribute("genders", Gender.values());
        model.addAttribute("am1", true);
        model.addAttribute("am2", true);

        model.addAttribute("hours", new Hours());
        model.addAttribute("therapist", new TherapistRegisterFormDTO());
        model.addAttribute("title", "This is just a test");


        return "therapistsignup/index";
    }

    @PostMapping()
    public String processTherapistSignup(@ModelAttribute @Valid TherapistRegisterFormDTO therapistRegisterFormDTO,
                                         Hours hours,
                                         Errors errors, HttpServletRequest request,
                                         @RequestParam(value = "startTime") int startTime,
                                         boolean isStartAM,
                                         @RequestParam(value = "finishTime") int finishTime,
                                         boolean isFinishAM,
                                         Model model) {

        User newUser = getUserFromSession(request.getSession());

        hours.setStartTime(startTime, isStartAM);
        hours.setFinishTime(finishTime, isFinishAM);

        therapistRegisterFormDTO.setHoursOfOperation(hours);

        Therapist newTherapist =
                new Therapist(newUser, therapistRegisterFormDTO.getGender(),
                       therapistRegisterFormDTO.getHoursOfOperation());



        therapistRepository.save(newTherapist);

        newUser.setTherapist(newTherapist);
        userRepository.save(newUser);


        return "redirect:";
    }

}
