package com.epam.training.controllers;

import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SportBettingController {

    @RequestMapping("/login")
    public String login(Model model,
                        @RequestParam(required = false) String error,
                        @RequestParam(required = false) String logout) {
        model.addAttribute("error", Objects.nonNull(error));
        model.addAttribute("logout", Objects.nonNull(logout));
        return "login";
    }

    @RequestMapping("/registration")
    public String registration() {
        return "index";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
