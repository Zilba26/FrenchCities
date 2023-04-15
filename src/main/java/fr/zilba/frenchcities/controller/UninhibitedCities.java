package fr.zilba.frenchcities.controller;

import fr.zilba.frenchcities.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class UninhibitedCities {

    @Autowired
    private CitiesService citiesService;

    @PostMapping("/uninhibited")
    @ResponseBody
    public ResponseEntity<String> uninhibitedCities() throws IOException {
        boolean isGood = citiesService.uninhibitedCities();
        if (isGood) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
