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

## Architecture MVVM
<img src="https://github.com/scerdan/my-new-challenge/blob/master/pictures/figure0.png" width="500"/>

- This app is based on the MVVM architecture and the Repository pattern, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).

### Architecture Overview
<img src="https://github.com/scerdan/my-new-challenge/blob/master/pictures/figure1.png" width="500"/>

- Each layer follows [unidirectional event/data flow](https://developer.android.com/topic/architecture/ui-layer#udf); the UI layer emits user events to the data layer, and the data layer exposes data as a stream to other layers.
- The data layer is designed to work independently from other layers and must be pure, which means it doesn't have any dependencies on the other layers.

With this loosely coupled architecture, you can increase the reusability of components and scalability of your app.

### UI Layer

<img src="https://github.com/scerdan/my-new-challenge/blob/master/pictures/figure2.png" width="500"/>

The UI layer consists of UI elements to configure screens that could interact with users and [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that holds app states and restores data when configuration changes.
- UI elements observe the data flow via [DataBinding](https://developer.android.com/topic/libraries/data-binding), which is the most essential part of the MVVM architecture. 
- With [Bindables](https://github.com/skydoves/bindables), which is an Android DataBinding kit for notifying data changes, you can implement two-way binding, and data observation in XML very clean.

### Data Layer

<img src="https://github.com/scerdan/my-new-challenge/blob/master/pictures/figure3.png" width="500"/>

The data Layer consists of repositories, which include business logic, such as querying data from the local database and requesting remote data from the network. It is implemented as an offline-first source of business logic and follows the [single source of truth](https://en.wikipedia.org/wiki/Single_source_of_truth) principle.<br>


## GitHub API Integration

This project integrates the GitHub API to retrieve user data, such as followers, following details, and profile information. The following API endpoints are used:

```gradle
GET https://api.github.com/users?per_page=20&page=1
Fetches a paginated list of users.

GET https://api.github.com/users/{login_username}
Retrieves detailed information about a specific user.

GET https://api.github.com/users/{login_username}/followers
Gets a list of followers for a specific user.

GET https://api.github.com/users/{login_username}/following{/other_user}
Retrieves the users a specific user is following.
```
These endpoints allow for dynamic interaction with GitHub's user data, enabling real-time updates in the application.

For more information, visit the official [GitHub API documentation](https://docs.github.com/en/rest).

## Main Dependencies
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


