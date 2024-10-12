# My GitHub Users App

This project is an Android application written in **Kotlin** using **Jetpack Compose** as the primary tool for the user interface. It is designed to manage and display GitHub users with additional functionalities, such as using an API to retrieve follower and following user information.

## Features

- **Jetpack Compose** for UI development.
- **Hilt** for dependency injection.
- **Retrofit** for communication with REST APIs.
- **Navigation Component** for navigating between screens.
- **Datastore** for user preference storage.
- **Coil** for efficient image loading.

## Screenshots

| Splash Screen | Home Screen | Detail Screen |
|-------------------|-------------------|----------------|
| <img src="https://github.com/scerdan/my-new-challenge/blob/master/pictures/checkin.png" width="250"/> | <img src="https://github.com/scerdan/my-new-challenge/blob/master/pictures/home.png" width="250"/> | <img src="https://github.com/scerdan/my-new-challenge/blob/master/pictures/detail.png" width="250"/> |

## Installation
Follow these steps to clone the project and set up dependencies:

1. Clone this repository:
    ```bash
    git clone https://github.com/scerdan/my-new-challenge.git
    ```
2. Open the project in **Android Studio**.
3. Sync the project to install the dependencies.
4. Run

## 🔻Important data to use the app‼️
In order to use the app, you will require a personal access token.  
You can create it by logging into GitHub and accessing:  
[https://github.com/settings/personal-access-tokens/](https://github.com/settings/personal-access-tokens/)

Otherwise you will navigate to the generic error screen where you can paste your access token and try to login again.

<img src="https://github.com/scerdan/my-new-challenge/blob/master/pictures/pasteToken.png" width="300"/>

## Requirements

- **Android Studio Giraffe (Canary or later)**.
- **Java 11** or higher.
- **Gradle 8.x**.

## Installation
Follow these steps to clone the project and set up dependencies:

1. Clone this repository:
    ```bash
    git clone https://github.com/yourusername/MyNewChallenge.git
    ```
2. Open the project in **Android Studio**.
3. Sync the project to install the dependencies.

## Project Setup

### Main Dependencies

This project uses the following key dependencies:

- **Jetpack Compose**: For declarative user interfaces.
- **Hilt**: Dependency injection for managing ViewModels.
- **Retrofit**: For making HTTP requests to the GitHub API.
- **Datastore**: For persistent data storage.
- **Coil**: Efficient image loading in the UI.

Here is a summary of the versions used:

```toml
[versions]
hiltAndroidVersion = "2.49"
retrofitVersion = "2.9.0"
composeBom = "2023.08.00"
coilCompose = "2.4.0"
# And more...
```
## Optimizations
What optimizations did you make in your code?



