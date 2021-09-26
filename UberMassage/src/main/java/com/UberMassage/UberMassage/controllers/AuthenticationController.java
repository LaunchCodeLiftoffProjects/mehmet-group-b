package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.CityRepository;
import com.UberMassage.UberMassage.data.StateRepository;
import com.UberMassage.UberMassage.data.TherapistRepository;
import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TherapistRepository therapistRepository;


    private static final String userSessionKey = "user";

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CityRepository cityRepository;


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


    @RequestMapping(value = "/register")
    public String displayRegistrationForm(@RequestParam(value = "stateId",required = false) Integer stateId, Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute(new User());


        model.addAttribute("states",stateRepository.findAll());

//        adding cities to model based on selected state
        ArrayList<String> cities = new ArrayList<>();
        for (City city:cityRepository.findAll()
             ) {
//                if(stateId != null && city.getState().getId() == stateId){
            String nextCity = city.getCity();
            cities.add(nextCity);
//            }
        }
        model.addAttribute("cities",cities);


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


        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:profile/" + newUser.getId() ;
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

        return "redirect:profile/" + theUser.getId() ;

    }




    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }


    //getting JSON list for city drop down on register form
    @RequestMapping(value="/cities", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<String> findCitiesInState(@RequestParam(value="selectedState")String selectedState){
        State selectedStateFilter = new State();
        for (State state:stateRepository.findAll()
        ) {
            if(selectedState.equals(state.getState())){
                selectedStateFilter = state;
            }
        }
        ArrayList <String> citiesFromSelectedState = new ArrayList<String>();
        for (City city: selectedStateFilter.getCities()
        ) {
            citiesFromSelectedState.add(city.getCity());
        }
        return citiesFromSelectedState;
    }
}
