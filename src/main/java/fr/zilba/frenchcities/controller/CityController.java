package fr.zilba.frenchcities.controller;

import fr.zilba.frenchcities.model.City;
import fr.zilba.frenchcities.model.Weather;
import fr.zilba.frenchcities.service.CitiesService;
import fr.zilba.frenchcities.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class CityController {

    @Autowired
    CitiesService citiesService;

    @Autowired
    WeatherService weatherService;

    @GetMapping("/city")
    public String get(Model model, @RequestParam(value = "code") String codeCommune) throws IOException {
        City city = citiesService.getCity(codeCommune);
        model.addAttribute("city", city);
        if (city == null) {
            model.addAttribute("error_message_get", "La ville avec le code de commune INSEE " + codeCommune + " n'existe pas.");
        } else {
            Weather weather = weatherService.getWeather(city);
            model.addAttribute("weather", weather);
        }
        return "city";
    }

    @PostMapping(value = "/city", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String post(RedirectAttributes redirectAttributes, Model model, @ModelAttribute City city, @RequestParam(required = false, name = "save") String isUpdate, @RequestParam(required = false, name = "delete") String isDelete) throws IOException {
        Weather weather = weatherService.getWeather(city);
        model.addAttribute("city", city);
        model.addAttribute("weather", weather);
        if (isUpdate != null) {
            boolean isGood = citiesService.updateCity(city);
            if (isGood) {
                model.addAttribute("temp_message", "Mise à jour de la ville effectué");
            } else {
                model.addAttribute("temp_message", "Un problème est survenu lors de la mise à jour de la ville");
            }
            return "city";
        } else if (isDelete != null) {
            boolean isGood = citiesService.deleteCity(city);
            if (isGood) {
                redirectAttributes.addFlashAttribute("delete_message", "Ville supprimée avec succès");
                return "redirect:/cities";
            } else {
                model.addAttribute("temp_message", "Un problème est survenu lors de la suppression de la ville");
                return "city";
            }
        } else {
            return null;
        }
    }
}