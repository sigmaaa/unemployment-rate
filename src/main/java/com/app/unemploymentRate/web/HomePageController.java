package com.app.unemploymentRate.web;

import com.app.unemploymentRate.JsonObjects.CountriesData;
import com.app.unemploymentRate.JsonObjects.FactoryCountriesData;
import com.app.unemploymentRate.JsonUrl;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@Controller
public class HomePageController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String showHomeForm(Model model) {
        model.addAttribute("jsonUrl", new JsonUrl());
        model.addAttribute("topBestCountries", new HashMap<String, Double>());
        model.addAttribute("topWorstCountries", new HashMap<String, Double>());
        model.addAttribute("allCountriesRate", new HashMap<String, Double>());
        return "home";
    }

    @PostMapping
    public String processJson(@ModelAttribute("jsonUrl") JsonUrl jsonUrl, Errors errors, Model model) throws IOException, JSONException {
        String response = restTemplate.getForObject(jsonUrl.getJsonUrl(), String.class);
        CountriesData dataFromJsonStat = new FactoryCountriesData().getCountriesData(response);

        model.addAttribute("topBestCountries", dataFromJsonStat.getCountriesWithLowestUR());
        model.addAttribute("topWorstCountries", dataFromJsonStat.getCountriesWithHighestUR());
        model.addAttribute("allCountriesRate", dataFromJsonStat.getAllCountriesStat());
        model.addAttribute("submit", true);

        return "home";
    }
}
