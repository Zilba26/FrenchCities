package fr.zilba.frenchcities.controller;

import fr.zilba.frenchcities.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class CitiesDistanceController {

    @Autowired
    CitiesService citiesService;

    @GetMapping("/cities-distance")
    public String get(Model model) throws IOException {
        model.addAttribute("cities", citiesService.getAllCities("Nom_commune"));
        return "cities_distances";
    }
}
