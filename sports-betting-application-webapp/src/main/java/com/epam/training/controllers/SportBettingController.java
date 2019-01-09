package com.epam.training.controllers;

import java.util.Objects;
import java.util.Optional;

import com.epam.training.dto.UserRegistrationData;
import com.epam.training.facade.UserFacade;
import com.epam.training.sportsbeatting.domain.user.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SportBettingController {

    @Autowired
    private UserFacade userFacade;

    @RequestMapping("/login")
    public String login(Model model,
                        @RequestParam(required = false) String error,
                        @RequestParam(required = false) String logout) {
        model.addAttribute("error", Objects.nonNull(error));
        model.addAttribute("logout", Objects.nonNull(logout));
        return "login";
    }

    @PostMapping("/registration")
    public String registration(UserRegistrationData userRegistrationData, Model model) {
        final Optional<Player> registeredUser = userFacade.registerUser(userRegistrationData);
        model.addAttribute("registered", registeredUser.isPresent());
        model.addAttribute("registrationError", !registeredUser.isPresent());
        return "login";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
