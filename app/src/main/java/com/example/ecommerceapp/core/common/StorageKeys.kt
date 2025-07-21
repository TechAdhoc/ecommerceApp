package com.example.ecommerceapp.core.common

/**
 * Constants for secure storage keys to maintain consistency and avoid typos
 */
object StorageKeys {
    // Session related keys
    const val AUTH_TOKEN = "auth_token"
    const val USER_ID = "user_id"
    const val USER_NAME = "user_name"
    const val USER_EMAIL = "user_email"
    const val IS_LOGGED_IN = "is_logged_in"
    const val LAST_LOGIN_TIMESTAMP = "last_login_timestamp"
    const val LAST_LOGOUT_TIMESTAMP = "last_logout_timestamp"
    const val LAST_LOGGED_IN_EMAIL = "last_logged_in_email"
    const val USER_STATE = "user_state"
    
    // App settings
    const val DARK_MODE = "dark_mode"
    const val NOTIFICATIONS_ENABLED = "notifications_enabled"
    const val APP_LANGUAGE = "app_language"
    
    // User preferences
    const val SELECTED_CURRENCY = "selected_currency"
    const val ADDRESS_PRIMARY = "address_primary"
    
    // Shopping related
    const val CART_ITEMS = "cart_items"
    const val WISHLIST_ITEMS = "wishlist_items"
    const val RECENT_SEARCHES = "recent_searches"
    const val RECENT_PRODUCTS = "recent_products"
}
