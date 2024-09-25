package org.example.service;

import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;

import java.net.URI;

@Service
public class SpotifyTransferService {

    private final SpotifyApi spotifyApi;

    public SpotifyTransferService(SpotifyApi spotifyApi) {
        this.spotifyApi = spotifyApi;
    }

    // Transfer playlists from source account to target account
    public void transferPlaylists(String sourceUserId, String targetUserId) {
        try {
            // Fetch playlists from the source user
            GetListOfCurrentUsersPlaylistsRequest getPlaylistsRequest = spotifyApi.getListOfCurrentUsersPlaylists()
                    .limit(20)
                    .build();

            Paging<PlaylistSimplified> playlistSimplifiedPaging = getPlaylistsRequest.execute();
            PlaylistSimplified[] playlists = playlistSimplifiedPaging.getItems();

            for (PlaylistSimplified playlist : playlists) {
                // Create a new playlist in the target account
                CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(targetUserId, playlist.getName())
                        .collaborative(playlist.getIsCollaborative())
                        .public_(playlist.getIsPublicAccess())
                        .description("Copied from another account") // Description added manually
                        .build();

                Playlist newPlaylist = createPlaylistRequest.execute();

                // Fetch tracks from the source playlist
                GetPlaylistsItemsRequest getTracksRequest = spotifyApi.getPlaylistsItems(playlist.getId())
                        .build();

                Paging<PlaylistTrack> playlistTrackPaging = getTracksRequest.execute();
                PlaylistTrack[] tracks = playlistTrackPaging.getItems();

                // Collect track URIs to add to the new playlist
                String[] trackUris = new String[tracks.length];
                for (int i = 0; i < tracks.length; i++) {
                    trackUris[i] = tracks[i].getTrack().getUri();
                }

                // Add tracks to the new playlist
                AddItemsToPlaylistRequest addItemsRequest = spotifyApi.addItemsToPlaylist(newPlaylist.getId(), trackUris)
                        .build();

                addItemsRequest.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle errors properly in production code
        }
    }
}