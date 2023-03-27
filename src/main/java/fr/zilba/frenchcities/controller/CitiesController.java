package fr.zilba.frenchcities.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CitiesController {


    @GetMapping("/cities")
    public String get(Model model) {
        return "cities";
    }
}
