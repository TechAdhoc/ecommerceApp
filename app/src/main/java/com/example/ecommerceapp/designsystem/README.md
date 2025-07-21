# Ecommerce App Design System Documentation

This document provides an overview of the design system architecture used in the Ecommerce App, based on the Atomic Design Methodology.

## Atomic Design Methodology

The design system follows the Atomic Design Methodology, which breaks down interfaces into five distinct levels:

1. **Atoms**: Basic building blocks (buttons, inputs, icons, text styles)
2. **Molecules**: Simple combinations of atoms to form small UI components
3. **Organisms**: Complex components composed of molecules and atoms
4. **Templates**: Page-level structures without real content
5. **Pages**: Specific instances of templates with real content

## Directory Structure

```
com.example.ecommerceapp.designsystem/
├── atoms/            # Basic UI components
├── molecules/        # Composite components 
├── organisms/        # Complex components
├── templates/        # Page layouts
└── theme/            # Design tokens and theme definitions
```

## Theme Components

The theme directory contains the foundational design tokens:

- **Color.kt**: Color palette for the app
- **Dimensions.kt**: Spacing, sizes, and other measurements
- **Shape.kt**: Shape definitions for components
- **Type.kt**: Typography styles
- **Theme.kt**: Theme composition for light and dark modes

## Atoms

Basic UI components:

- **Button.kt**: Various button styles (primary, secondary, text)
- **Icon.kt**: Icon components with standardized sizing
- **Surface.kt**: Surface and divider components
- **Text.kt**: Text components with standardized styles
- **TextField.kt**: Input field components

## Molecules

Combinations of atoms:

- **EcommerceCard.kt**: Card components with standardized layout
- **FormField.kt**: Form field with label and validation
- **InfoBanner.kt**: Information and error message components
- **ProductItemCard.kt**: Product display card

## Organisms

Complex components:

- **AppTopBar.kt**: Application top bar with various configurations
- **BottomNavigationBar.kt**: Bottom navigation with icons and labels
- **LoginForm.kt**: Complete login form with validation
- **ProductGrid.kt**: Grid and carousel components for product listings

## Templates

Page layouts:

- **StandardScreen.kt**: Standard screen layout with app bars and content area
- **FormTemplates.kt**: Templates for form-based screens

## Usage Guidelines

### Using the Theme

```kotlin
@Composable
fun YourScreen() {
    ECommerceAppTheme {
        // Your UI components here
    }
}
```

### Accessing Theme Values

```kotlin
@Composable
fun YourComponent() {
    val primary = MaterialTheme.colors.primary
    val spacing = EcommerceDesignSystem.spacing.medium
    
    // Use these values in your UI
}
```

### Component Examples

**Primary Button**:
```kotlin
EcommercePrimaryButton(
    onClick = { /* action */ }
) {
    Text("Add to Cart")
}
```

**Product Grid**:
```kotlin
ProductGrid(
    products = productList,
    onProductClick = { /* navigate to product */ },
    onAddToCartClick = { /* add to cart */ }
)
```

**Standard Screen**:
```kotlin
StandardScreen(
    title = "Home",
    navController = navController,
    bottomNavItems = bottomNavItems
) { paddingValues ->
    // Your screen content here
}
```

## Best Practices

1. Always use design system components instead of creating new ones
2. Follow the hierarchy: atoms → molecules → organisms → templates → pages
3. Use the theme values for colors, spacing, and typography
4. Maintain consistency by reusing components
5. When extending the design system, follow the existing patterns and naming conventions
