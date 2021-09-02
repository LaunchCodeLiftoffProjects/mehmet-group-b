package com.UberMassage.UberMassage.controllers;

import com.UberMassage.UberMassage.data.AppointmentRepository;
import com.UberMassage.UberMassage.data.CityRepository;
import com.UberMassage.UberMassage.data.StateRepository;
import com.UberMassage.UberMassage.data.UserRepository;
import com.UberMassage.UberMassage.models.*;
import com.UberMassage.UberMassage.models.dto.AppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    StateRepository stateRepository;
    @Autowired
    CityRepository cityRepository;

    private static final String userSessionKey = "client";

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
    public String displaySchedule(@RequestParam(required = false) String searchState,@RequestParam(required = false)String searchCity, Model model, HttpServletRequest request) {


        User theUser = getUserFromSession(request.getSession());

        model.addAttribute("title", "This is schedule");
        model.addAttribute("client", theUser);
        model.addAttribute("states",stateRepository.findAll());
        model.addAttribute("searchState",searchState);

//        Search by state
        if(searchState != null) {
            ArrayList<User> usersByState = new ArrayList<User>() {};
            Iterable<User> users = userRepository.findAll();
            for (User user:users) {
                if (searchState.equals(user.getState())){
                    usersByState.add(user);};
            }

            //gets cities by selected state filter
            State selectedStateFilter = new State();
            for (State state:stateRepository.findAll()
                 ) {
                if(searchState.equals(state.getState())){
                     selectedStateFilter = state;
                }
            }
            ArrayList <City> citiesFromSelectedState = new ArrayList<City>();
            for (City city: selectedStateFilter.getCities()
                 ) {
                citiesFromSelectedState.add(city);
            }
            model.addAttribute("cities",citiesFromSelectedState);


            //search by city
            if(searchCity != null){
                ArrayList<User> usersByCity = new ArrayList<User>();
                for (User user:usersByState
                     ) {
                    if(searchCity.equals(user.getCity())){
                        usersByCity.add(user);
                        model.addAttribute("users",usersByCity);
                    }
                }
            }
            else { //if no city
            model.addAttribute("users", usersByState);
            }
        }
        else { //if no state
            model.addAttribute("users",userRepository.findAll());
        }




        return "schedule/index";
    }

    @PostMapping("")
    public String handleButton(HttpServletRequest request,
                               @RequestParam int therapistId,
                               Model model) {
        User theUser = getUserFromSession(request.getSession());
        model.addAttribute("title", "This is schedule");
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("client", theUser);

        User therapist = userRepository.findById(therapistId).orElse(new User());

        Appointment newAppointment = new Appointment(therapist,
                    theUser);

        theUser.setAppointment(newAppointment);
        therapist.setAppointment(newAppointment);

        appointmentRepository.save(newAppointment);

        userRepository.save(theUser);
        userRepository.save(therapist);

        return "schedule/index";
    }
}
