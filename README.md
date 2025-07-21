# ECommerceApp

## Architecture Overview

This ECommerce application is built following modern Android development practices, incorporating the Atomic Design System approach along with MVVM Clean Architecture and unidirectional data flow principles.

## Atomic Design System

The UI components are structured according to the Atomic Design methodology, which provides a clear hierarchy for organizing UI elements using jetpack compose:

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
- **View**: Activity, Composables
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
│   ├── components/     # Reusable UI components
│   ├── designsystem/   # Atomic Design System components
│   │   ├── atoms/      # Basic UI components
│   │   ├── molecules/  # Composite components
│   │   ├── organisms/  # Complex UI components
│   │   ├── templates/  # Page layouts
│   │   └── theme/      # Design tokens and theme
│   ├── network/        # Network configuration
│   ├── database/       # Local database setup
│   ├── di/            # Dependency injection modules
│   ├── base/          # Base classes and interfaces
│   ├── common/        # Common utilities and extensions
│   └── session/       # User session management
├── data/
│   ├── repository/    # Repository implementations
│   ├── remote/        # API service interfaces
│   ├── local/         # Database operations
│   └── model/         # Data models and mappers
├── domain/
│   ├── model/         # Domain entities
│   ├── repository/    # Repository interfaces
│   └── usecase/       # Business logic use cases
└── feature/
    ├── auth/          # Authentication screens
    ├── home/          # Home and product listing screens
    ├── product/       # Product detail screens
    ├── cart/          # Shopping cart screens
    └── checkout/      # Checkout flow screens
```

## Design System Integration

The design system is now centralized in the core module, making it a foundational part of the application architecture. This organization provides several benefits:

- **Consistent UI/UX**: Ensures visual consistency across all features by centralizing design tokens and components
- **Improved Maintainability**: Changes to the design system affect the entire app uniformly
- **Better Developer Experience**: Clear component hierarchy following Atomic Design principles
- **Enhanced Reusability**: Core UI components can be easily used across different features

### Theme Usage

The Theme object provides direct access to colors, typography, and dimensions:

```kotlin
// Colors
Text(color = Theme.primary)

// Typography
Text(style = Theme.bodyMedium)

// Dimensions
Spacer(modifier = Modifier.height(Dimensions.spacing_16))
```

## Getting Started

To get started with the project, follow these steps:

1. **Clone the repository**: `git clone /ecommerceapp.git`
2. **Open in Android Studio**: Import the project as an existing Android Studio project.
3. **Build the project**: Wait for Gradle to sync and build the project.
4. **Run the app**: Connect a device or start an emulator, then run the app from Android Studio.

## Contributing

Pull requests are welcome and help this to make a cool next gen android framework with jetpack compose.
