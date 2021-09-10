package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.CityRepository;
import com.UberMassage.UberMassage.data.StateRepository;
import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.City;
import com.UberMassage.UberMassage.models.State;
import com.UberMassage.UberMassage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

     @Autowired
    StateRepository stateRepository;
    @Autowired
    CityRepository cityRepository;

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
    public String displayHomepage(@ModelAttribute State newState,Model model, HttpServletRequest request) {
        User theUser = getUserFromSession(request.getSession());

        model.addAttribute("title", "Hello World");
        model.addAttribute("user", theUser);

//        City Columbia = new City("Columbia");
//        City Joplin = new City("Joplin");
//        City KansasCity = new City("Kansas City");
//        City Kirksville = new City("Kirksville");
//        City LakeOfTheOzarks= new City("Lake of the Ozarks");
//        City SoutheastMO = new City("Southeast MO");
//        City Springfield = new City("Springfield");
//        City StJoseph = new City("St. Joseph");
//        City StLouis = new City("St. Louis");
//
//
//        cityRepository.save(Columbia);
//        cityRepository.save(Joplin);
//        cityRepository.save(KansasCity);
//        cityRepository.save(Kirksville);
//        cityRepository.save(LakeOfTheOzarks);
//        cityRepository.save(SoutheastMO);
//        cityRepository.save(Springfield);
//        cityRepository.save(StJoseph);
//        cityRepository.save(StLouis);

//    for (int i = 36; i < 45; i++) {
//        Optional<City> cityresult = cityRepository.findById(i);
//        City acity = cityresult.get();
//        Optional<State> stateresult = stateRepository.findById(35);
//        State astate = stateresult.get();
//
//        acity.setState(astate);
//        cityRepository.save(acity);
//    }

        return "index";
    }
}
