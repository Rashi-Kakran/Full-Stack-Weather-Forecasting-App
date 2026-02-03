package com.wApp.WeatherForcasting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth")
public class OAuthController {

    @GetMapping("/login")
    public String oauthLogin() {
        return "Use /oauth2/authorization/google";
    }
}
