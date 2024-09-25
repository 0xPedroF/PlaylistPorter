# PlaylistPorter

**PlaylistPorter** is a web application that allows users to transfer their playlists from one Spotify account to another seamlessly. It leverages the Spotify Web API to authenticate users, fetch playlists, and recreate them in the target account, making it easier for users who are switching accounts or consolidating their music.

## Features

- **Authenticate with Spotify**: Secure OAuth2 authentication to connect to Spotify accounts.
- **Transfer Playlists**: Easily transfer playlists from one Spotify account to another.
- **Retain Playlist Details**: Maintain the original playlist's name, public/private status, and track order.
- **Supports Large Playlists**: Handles large playlists efficiently by managing paginated requests to the Spotify API.

## Technologies Used

- **Java 17**: Core language used for the application.
- **Spring Boot 3.1.4**: Framework used for creating RESTful web services and dependency injection.
- **Spotify Web API (Java Wrapper)**: Used to interact with Spotify's API for managing playlists.
- **Maven**: Build automation and dependency management tool.
- **Tomcat**: Embedded web server for running the application.