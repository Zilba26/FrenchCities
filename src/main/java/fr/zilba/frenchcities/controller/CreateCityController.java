package fr.zilba.frenchcities.controller;

import fr.zilba.frenchcities.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class CreateCityController {

    @Autowired
    private CitiesService citiesService;

    @PostMapping("/create")
    public String create(@RequestParam(value = "city_code") String cityCode, RedirectAttributes attributes) throws IOException {
        boolean isGood = citiesService.createCity(cityCode);
        if (isGood) {
            attributes.addFlashAttribute("temp_message", "Ville créée avec succès");
        } else {
            attributes.addFlashAttribute("temp_message", "La ville avec ce code existe déjà");
        }
        return "redirect:/city?code=" + cityCode;
    }
}
