# ECommerceApp

## Architecture Overview

This ECommerce application is built following modern Android development practices, incorporating the Atomic Design System approach along with MVVM Clean Architecture and unidirectional data flow principles.

## Atomic Design System

The UI components are structured according to the Atomic Design methodology, which provides a clear hierarchy for organizing UI elements:

### Atoms
Atoms are the smallest building blocks of the interface:
- Typography components
- Buttons
- Input fields
- Icons
- Color palettes

### Molecules
Molecules are groups of atoms that work together as a unit:
- Search bars (combining input field and search button)
- Form input groups
- Product cards
- Rating components

### Organisms
Organisms are complex UI components composed of molecules and atoms:
- Navigation bars
- Product listings
- Shopping cart summaries
- Checkout forms
- Product detail sections

### Templates
Templates are page-level objects that place components into a layout:
- Product list screen layout
- Product detail screen layout
- Checkout flow layout

### Pages
Pages are specific instances of templates with real content:
- Home page
- Product category page
- Product detail page
- Cart page
- Checkout page

## Clean Architecture

The application follows Clean Architecture principles with clear separation of concerns:

### Presentation Layer
- **View**: Activities, Fragments, Composables
- **ViewModel**: Manages UI state and business logic
- **UI State**: Immutable data classes representing the UI state

### Domain Layer
- **Use Cases**: Single-responsibility classes for business logic
- **Models**: Business entities
- **Repository Interfaces**: Abstractions for data operations

### Data Layer
- **Repository Implementations**: Data source coordination
- **Remote Data Source**: API services
- **Local Data Source**: Database operations
- **Models**: Data transfer objects (DTOs)

## Unidirectional Data Flow

The app implements unidirectional data flow using the following pattern:

1. **User Actions** → Trigger events (e.g., button clicks, form submissions)
2. **ViewModel** → Processes events, executes use cases
3. **Use Cases** → Execute business logic via repositories
4. **Repository** → Manages data operations across sources
5. **ViewModel** → Updates UI state based on results
6. **View** → Renders UI based on the state

This approach ensures:
- Predictable state management
- Easier debugging
- Better testability
- Clear separation of concerns

## Technologies and Libraries

- **Kotlin**: Primary programming language
- **Jetpack Compose**: Modern UI toolkit
- **Coroutines & Flow**: Asynchronous programming
- **Hilt**: Dependency injection
- **Room**: Local database
- **Retrofit**: Network requests
- **Navigation Component**: In-app navigation
- **Unit Testing**: JUnit, Mockk
- **UI Testing**: Compose UI testing

## Project Structure

The project follows a feature-based package structure, where each feature contains its own implementation of the Clean Architecture layers:

```
app/
├── core/
│   ├── di/
│   ├── network/
│   ├── database/
│   └── utils/
├── features/
│   ├── auth/
│   │   ├── data/
│   │   ├── domain/
│   │   └── presentation/
│   ├── products/
│   │   ├── data/
│   │   ├── domain/
│   │   └── presentation/
│   ├── cart/
│   │   ├── data/
│   │   ├── domain/
│   │   └── presentation/
│   └── checkout/
│       ├── data/
│       ├── domain/
│       └── presentation/
├── design_system/
│   ├── atoms/
│   ├── molecules/
│   ├── organisms/
│   └── theme/
└── utils/
```

## Getting Started

Instructions for setting up and running the project locally...
Clone the project, open with Android studio and run the app

## Contributing

Guidelines for contributing to the project...

## License

License information...
