
# Android Clean Architecture

This document outlines the project structure, class responsibilities, and design decisions for the Android application.

## Project structure
The project is build using Clean Architecture principles, to seperate code into distinct layers.
- **Data:** Handles data from local and remote source
- **Domain:** Holds business logic and entities.
- **Presentation:** Contains UI components, fragments, activities, viewmodels and adaptors.

## Package structure
- **shared:** This package contains code that is shared between different layers of the application.
    - *data:* This subpackage contains data classes and mappers.
    - *model:* This subpackage contains general data classes like APIResponse and Support.
    - *di:* This subpackage contains dependency injection modules like DatabaseModule and NetworkModule.
- **user:** This package contains features related to users.
    - *data:* This subpackage contains user-specific data classes and data access logic.
        - *api:* This subpackage contains interfaces for interacting with the user API.
        - *db:* This subpackage contains interfaces for interacting with the user database.
        - *di:* This subpackage contains the ServiceModule which provides dependencies for the user feature.
        - *mediator:* This subpackage contains the UserRemoteMediator which handles pagination of user data.
        - *model:* This subpackage contains user-specific data classes like UserEntity and UserRemoteKeys.
    - *domain:* This subpackage contains the business logic for the user feature.
    - *presentation:* This subpackage contains the presentation layer for the user feature, including the UserListAdapter, UserListFragment, UserPagingAdapter, UserViewModel, and MainActivity.

## Class Responsibilities:
- *AppDatabase:* This class represents the room database for the application.
- *DatabaseModule and NetworkModule:* These classes provide dependencies for the database and network calls respectively.
- APIResponse:Eencapsulates paginated API response data with other data and a generic data object (T)
- *UserService:* This interface defines methods for fetching user data from the API.
- *UserDao:* This interface defines methods for interacting with the user data in the database.
- *UserRemoteKeysDao:* This interface defines methods for interacting with the user remote keys data in the database.
- *ServiceModule:* These class provide dependencies for API services.
- *UserRemoteMediator:* This class handles pagination of user data by fetching data from the API and storing it in the database.
- *UserEntity:* This class represents a user entity.
- *UserRemoteKeys:* This class represents the keys used for pagination of user data.
- *UserRepository:* This class is responsible for fetching user data from either the local database or the remote API.
- *UserPagingAdapter:* This class is responsible for displaying a paged list of users in the UI.
- *MainActivity:* This activity used to hold the fragments.
- *UserListFragment:* This fragment displays a list of users.
- *UserViewModel:* This ViewModel class is responsible for preparing and managing the data for the UserList UI.
- 
## Design Decisions:
- Project uses Dagger for dependency injection. This helps to improve the modularity and testability of the code.
- Project uses Room for local database persistence. This helps to store data offline and improve the performance of the application.
- Used Retrofit for networking calls. This is a popular library for making HTTP requests in Android applications.
- Used Glide for loading images.
- The project adopts SDP (density-independent pixels) and SSP (scaled pixels) to ensure consistent UI sizes and text sizes across different screen densities and font scaling settings.
- The project uses Paging 3 to efficiently load and display large datasets in a paginated manner, improving performance and user experience.

