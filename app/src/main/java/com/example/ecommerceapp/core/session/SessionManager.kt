package com.example.ecommerceapp.core.session

import com.example.ecommerceapp.core.common.SecureStorage
import com.example.ecommerceapp.core.common.StorageKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val secureStorage: SecureStorage
) {
    companion object {
        // Secure key names for session data - using centralized StorageKeys
        private val TOKEN_KEY = StorageKeys.AUTH_TOKEN
        private val USER_ID_KEY = StorageKeys.USER_ID
        private val USER_NAME_KEY = StorageKeys.USER_NAME
        private val USER_EMAIL_KEY = StorageKeys.USER_EMAIL
        private val IS_LOGGED_IN_KEY = StorageKeys.IS_LOGGED_IN
    }

    /**
     * Save user session data after successful login
     */
    fun saveSession(token: String, userId: String, name: String, email: String) {
        secureStorage.storeString(TOKEN_KEY, token)
        secureStorage.storeString(USER_ID_KEY, userId)
        secureStorage.storeString(USER_NAME_KEY, name)
        secureStorage.storeString(USER_EMAIL_KEY, email)
        secureStorage.storeBoolean(IS_LOGGED_IN_KEY, true)
    }

    /**
     * Get auth token
     */
    fun getToken(): Flow<String?> {
        return flow {
            val token = secureStorage.getString(TOKEN_KEY)
            emit(if (token.isNotEmpty()) token else null)
        }
    }
    
    /**
     * Get user ID
     */
    fun getUserId(): Flow<String?> {
        return flow {
            val userId = secureStorage.getString(USER_ID_KEY)
            emit(if (userId.isNotEmpty()) userId else null)
        }
    }

    /**
     * Get user name
     */
    fun getUserName(): Flow<String?> {
        return flow {
            val name = secureStorage.getString(USER_NAME_KEY)
            emit(if (name.isNotEmpty()) name else null)
        }
    }

    /**
     * Get user email
     */
    fun getUserEmail(): Flow<String?> {
        return flow {
            val email = secureStorage.getString(USER_EMAIL_KEY)
            emit(if (email.isNotEmpty()) email else null)
        }
    }

    /**
     * Check if user is logged in
     */
    fun isLoggedIn(): Flow<Boolean> {
        return getToken().map { token ->
            !token.isNullOrBlank()
        }
    }

    /**
     * Logout user - clear all session data
     */
    fun logout() {
        secureStorage.remove(TOKEN_KEY)
        secureStorage.remove(USER_ID_KEY)
        secureStorage.remove(USER_NAME_KEY)
        secureStorage.remove(USER_EMAIL_KEY)
        secureStorage.storeBoolean(IS_LOGGED_IN_KEY, false)
    }

    /**
     * Get the complete user session data as a map
     */
    fun getSessionData(): Flow<Map<String, String>> {
        return flow {
            val sessionMap = mutableMapOf<String, String>()
            secureStorage.getString(TOKEN_KEY).let { if (it.isNotEmpty()) sessionMap[TOKEN_KEY] = it }
            secureStorage.getString(USER_ID_KEY).let { if (it.isNotEmpty()) sessionMap[USER_ID_KEY] = it }
            secureStorage.getString(USER_NAME_KEY).let { if (it.isNotEmpty()) sessionMap[USER_NAME_KEY] = it }
            secureStorage.getString(USER_EMAIL_KEY).let { if (it.isNotEmpty()) sessionMap[USER_EMAIL_KEY] = it }
            emit(sessionMap)
        }
    }

    /**
     * Check if the session token is valid (not expired)
     * Note: This is a placeholder. In a real app, you would verify with the backend
     * or check JWT expiration if using JWT tokens
     */
    fun isSessionValid(): Flow<Boolean> {
        return flow {
            // In a real app, you would check token expiration
            // For now, just check if token exists
            emit(secureStorage.getString(TOKEN_KEY).isNotEmpty())
        }
    }
}
