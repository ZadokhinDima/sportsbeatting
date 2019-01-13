package com.epam.training.controllers;

import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import com.epam.training.dto.UserData;
import com.epam.training.dto.UserUpdateData;
import com.epam.training.facade.UserFacade;
import com.epam.training.facade.WagerFacade;
import com.epam.training.sportsbeatting.domain.user.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SportBettingController {

    @Autowired
    private UserFacade userFacade;
    @Autowired
    private WagerFacade wagerFacade;

    @RequestMapping("/login")
    public String login(Model model,
                        @RequestParam(required = false) String error,
                        @RequestParam(required = false) String logout) {
        model.addAttribute("error", Objects.nonNull(error));
        model.addAttribute("logout", Objects.nonNull(logout));
        return "login";
    }

    @PostMapping("/registration")
    public String registration(@Valid UserData userData, Model model) {
        final Optional<Player> registeredUser = userFacade.registerUser(userData);
        model.addAttribute("registered", registeredUser.isPresent());
        model.addAttribute("registrationError", !registeredUser.isPresent());
        return "login";
    }

    @RequestMapping("/")
    public String rootRedirect() {
        return "redirect:/home";
    }

    @PutMapping("/home/user")
    public String updateUserInfo(@Valid UserUpdateData userData) {
        userFacade.updateUserInfo(userData);
        return "redirect:/home";
    }

    @RequestMapping("/home")
    @Secured("ROLE_PLAYER")
    public String home(Model model) {
        model.addAttribute("user", userFacade.getCurrentPlayerInfo());
        model.addAttribute("wagers", wagerFacade.getAllWagersForCurrentUser());
        return "home";
    }

    @RequestMapping("/events")
    @Secured("ROLE_PLAYER")
    public String events() {
        return "events";
    }

    @DeleteMapping("/home/wager")
    public String deleteWager(@RequestParam Long id) {
        wagerFacade.deleteWager(id);
        return "redirect:/home";
    }

}
