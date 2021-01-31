package fr.isima.tp1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* Controller de test pour vérifier que SpringBoot fonctionne bien */
@RestController
public class GreetingController {
    @RequestMapping("/")
    public String greeting() {
        return "Yo from SpringBoot";
    }
}

