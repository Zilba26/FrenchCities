package fr.zilba.frenchcities.controller;

import fr.zilba.frenchcities.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class CitiesController {

    @Autowired
    CitiesService citiesService;

    @GetMapping("/cities")
    public String get(Model model, @RequestParam(name = "page", required = false) Integer page,
                      @RequestParam(name = "order", required = false) String order ) throws IOException, InterruptedException {
        if (page == null) page = 1;
        model.addAttribute("cities", citiesService.getAllCities(page, order));
        model.addAttribute("page", page - 1);
        model.addAttribute("maxPage", citiesService.getMaxPage());
        model.addAttribute("order", order);
        return "cities";
    }
}
