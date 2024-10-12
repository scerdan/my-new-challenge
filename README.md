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
What could we optimize in this project?
A lot of things!
For starters, it was done in a few days, so it has a lot of room for optimization. Next I'll go on to optimize some of the edges that we could attack in future features.


### UI:
Starting from the most generic to the most particular. As far as the UI is concerned, we have no clear theme and no dark theme. And we are also using a very small color palette.

```kotlin
val ColorPrimary = Color(0xFF568FA6)
val ColorSecondary = Color(0xFFA7DDF2)
val ColorBackground = Color(0xFFF2F2F2)
val ColorBlack = Color(0xFF0D0D0D)
```
### Testing:
We can also include everything that is testing since it has not been developed.
Although we already have the following tools in the project:

- JUnit 4:
```gradle
junit = { group = "junit", name = "junit", version.ref = "junit" }
```

- JUnit para AndroidX:
```gradle
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
```

- Espresso:
```gradle
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
```

- Compose UI Test (JUnit4):
```gradle
androidx-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
```

- Compose UI Test Manifest:
```gradle
androidx-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
```


