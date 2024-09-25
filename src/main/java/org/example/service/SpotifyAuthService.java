package org.example.service;

import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.net.URI;

@Service
public class SpotifyAuthService {

    private static final String CLIENT_ID = "your_client_id";  //Replace with your actual Client ID
    private static final String CLIENT_SECRET = "your_client_secret"; // Replace with your actual Client Secret
    private static final URI REDIRECT_URI = SpotifyHttpManager.makeUri("http://localhost:8080/callback");

    private final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .setRedirectUri(REDIRECT_URI)
            .build();

    // Generates the Spotify Authorization URL for user login
    public URI getAuthorizationUri() {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope("playlist-read-private playlist-modify-private playlist-modify-public")
                .show_dialog(true)
                .build();

        return authorizationCodeUriRequest.execute();
    }

    // Add more methods for handling access tokens, refreshing tokens, etc.
}
