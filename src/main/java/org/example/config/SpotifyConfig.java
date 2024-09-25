package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;

@Configuration
public class SpotifyConfig {

    private static final String clientId = "your_client_id"; // Replace with your Spotify client ID
    private static final String clientSecret = "your_client_secret"; // Replace with your Spotify client secret
    private static final String redirectUri = "your_redirect_uri"; // Replace with your redirect URI

    @Bean
    public SpotifyApi spotifyApi() {
        return new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(SpotifyHttpManager.makeUri(redirectUri))
                .build();
    }
}