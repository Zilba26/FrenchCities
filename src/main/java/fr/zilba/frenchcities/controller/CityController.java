package fr.zilba.frenchcities.controller;

import fr.zilba.frenchcities.model.City;
import fr.zilba.frenchcities.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class CityController {

    @Autowired
    CitiesService citiesService;

    @GetMapping("/city")
    public String get(Model model, @RequestParam(value = "code") String codeCommune) throws IOException {
        City city = citiesService.getCity(codeCommune);
        model.addAttribute("city", city);
        if (city == null) {
            model.addAttribute("error_message_get", "La ville avec le code de commune INSEE " + codeCommune + " n'existe pas.");
        }
        return "city";
    }

    @PostMapping(value = "/city", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String post(Model model, @ModelAttribute City city) throws IOException {
        boolean isGood = citiesService.updateCity(city);
        if (!isGood) model.addAttribute("error_message_update", "Error while updating the city.");
        model.addAttribute("city", city);
        return "city";
    }
}
