package org.example.controller;

import org.example.service.SpotifyAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/spotify")
public class SpotifyController {
    private final SpotifyAuthService spotifyAuthService;

    public SpotifyController(SpotifyAuthService spotifyAuthService) {
        this.spotifyAuthService = spotifyAuthService;
    }

    @GetMapping("/login")
    public String login() {
        // Redirect to Spotify’s authorization page
        return "redirect:" + spotifyAuthService.getAuthorizationUri();
    }

    // Add more endpoints for handling callbacks and playlist transfers
}
