package org.example.service;

import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;

public class SpotifyTransferService {
    private final SpotifyApi spotifyApi;

    public SpotifyTransferService(SpotifyApi spotifyApi) {
        this.spotifyApi = spotifyApi;
    }

    public void transferPlaylists(String sourceUserId, String targetUserId) {
        // Fetch playlists from source user
        PlaylistSimplified[] playlists = spotifyApi.getListOfCurrentUsersPlaylists().build().execute();

        for (PlaylistSimplified playlist : playlists) {
            // Create new playlist in target account
            CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(targetUserId, playlist.getName())
                    .collaborative(false)
                    .public_(playlist.getIsPublicAccess())
                    .description(playlist.getUri())
                    .build();

            Playlist newPlaylist = createPlaylistRequest.execute();

            // Transfer tracks from source playlist to target playlist (you'll need to fetch tracks and add them)
        }
    }
}
