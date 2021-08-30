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

//        City Bloomington = new City("Bloomington");
//        City ChampaignUrbana = new City("Champaign Urbana");
//        City Chicago = new City("Chicago");
//        City Decatur = new City("Decatur");
//        City LsSalleCo = new City("La Salle Co");
//        City Charleston = new City("Mattoon-Charleston");
//        City Peoria = new City("Peoria");
//        City QuadCities = new City("Quad Cities");
//        City Rockford = new City("Rockford");
//        City SouthernIL = new City("Southern IL");
//        City Springfield = new City("Springfield");
//        City StLouis = new City("St. Louis");
//        City WesternIl = new City("Western IL");

        //        cityRepository.save(Bloomington);
//        cityRepository.save(ChampaignUrbana);
//        cityRepository.save(Chicago);
//        cityRepository.save(Decatur);
//        cityRepository.save(LsSalleCo);
//        cityRepository.save(Charleston);
//        cityRepository.save(Peoria);
//        cityRepository.save(QuadCities);
//        cityRepository.save(Rockford);
//        cityRepository.save(SouthernIL);
//        cityRepository.save(Springfield);
//        cityRepository.save(StLouis);
//        cityRepository.save(WesternIl);
//        cities.add(Bloomington);
//        cities.add(ChampaignUrbana);
//        cities.add(Chicago);
//        cities.add(Decatur);
//        cities.add(LsSalleCo);
//        cities.add(Charleston);
//        cities.add(Peoria);
//        cities.add(QuadCities);
//        cities.add(Rockford);
//        cities.add(SouthernIL);
//        cities.add(Springfield);
//        cities.add(StLouis);
//        cities.add(WesternIl);


//        Optional<City> cityresult = cityRepository.findById(28);
//        City acity = cityresult.get();
//        Optional<State> stateresult = stateRepository.findById(34);
//        State astate = stateresult.get();
//        acity.setState(astate);
//        cityRepository.save(acity);


        return "index";
    }
}
