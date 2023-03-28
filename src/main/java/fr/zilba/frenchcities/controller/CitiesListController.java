package fr.zilba.frenchcities.controller;

import fr.zilba.frenchcities.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class CitiesListController {

    @Autowired
    CitiesService citiesService;

    @GetMapping("/cities")
    public String get(Model model) throws IOException {
        model.addAttribute("cities", citiesService.getAllCitiesPaginees());
        if (model.getAttribute("page") == null) {
            model.addAttribute("page", 0);
        }
        return "cities";
    }

    @PostMapping("/cities")
    public String post(Model model, @RequestParam(value = "page") String page) throws IOException {
        Integer pageInt = Integer.parseInt(page);
        model.addAttribute("page", pageInt);
        return this.get(model);
    }
}
