package fr.zilba.frenchcities.controller;

import fr.zilba.frenchcities.model.City;
import fr.zilba.frenchcities.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class CityController {

    @Autowired
    CitiesService citiesService;

    @GetMapping("/city")
    public String get(Model model, @RequestParam(value = "code") String codeCommune) throws IOException {
        City city = citiesService.getCity(codeCommune);
        model.addAttribute("city", city);
        return "city";
    }
}
