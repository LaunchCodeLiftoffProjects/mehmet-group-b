package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.TherapistRepository;
import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.Gender;
import com.UberMassage.UberMassage.models.Therapist;
import com.UberMassage.UberMassage.models.User;
import com.UberMassage.UberMassage.models.dto.TherapistRegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("therapistsignup")
public class ThereapistSignupController {

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
    public String displayTherapistSignupForm(Model model) {
        model.addAttribute("genders", Gender.values());

        model.addAttribute("therapist",new TherapistRegisterFormDTO());
        model.addAttribute("title", "This is just a test");
        return "therapistsignup/index";
    }

    @PostMapping()
    public String processTherapistSignup(@ModelAttribute @Valid TherapistRegisterFormDTO therapistRegisterFormDTO,
                                         Errors errors, HttpServletRequest request,
                                         Model model) {



        User newUser = getUserFromSession(request.getSession());

        Therapist newTherapist =
                new Therapist(newUser, therapistRegisterFormDTO.getGender());
        therapistRepository.save(newTherapist);

        newUser.setTherapist(newTherapist);
        userRepository.save(newUser);


        return "redirect:";
    }

}
