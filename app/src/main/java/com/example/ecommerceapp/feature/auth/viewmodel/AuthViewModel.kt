package com.example.ecommerceapp.feature.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.core.common.SecureStorage
import com.example.ecommerceapp.core.common.StorageKeys
import com.example.ecommerceapp.core.network.ApiResult
import com.example.ecommerceapp.core.session.SessionManager
import com.example.ecommerceapp.feature.auth.state.LoginFormState
import com.example.ecommerceapp.feature.auth.state.UserState
import com.example.ecommerceapp.domain.usecase.LoginUseCase
import com.example.ecommerceapp.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val sessionManager: SessionManager,
    private val secureStorage: SecureStorage
) : ViewModel() {

    private val _loginState = MutableStateFlow<ApiResult<Unit>>(ApiResult.Initial)
    val loginState: StateFlow<ApiResult<Unit>> = _loginState.asStateFlow()

    private val _logoutEvent = MutableStateFlow(false)
    val logoutEvent: StateFlow<Boolean> = _logoutEvent.asStateFlow()

    private val _uiEvent = MutableSharedFlow<AuthUiEvent>()
    val uiEvent: SharedFlow<AuthUiEvent> = _uiEvent.asSharedFlow()

    val isLoggedIn = sessionManager.isLoggedIn()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = ApiResult.Loading
            delay(3000)
            _loginState.value = ApiResult.Success(Unit)
            _uiEvent.emit(AuthUiEvent.LoginSuccess)
           /* loginUseCase(email, password).collect { result ->
                when (result) {
                    is ApiResult.Success -> {
                        _loginState.value = ApiResult.Success(Unit)
                        
                        // Store additional session information
                        storeSessionState(result.data.user.email, result.data.user.name)
                        
                        _uiEvent.emit(AuthUiEvent.LoginSuccess)
                    }
                    is ApiResult.Error -> {
                        _loginState.value = ApiResult.Error(result.message)
                        _uiEvent.emit(AuthUiEvent.Error(result.message))
                    }
                    ApiResult.Loading -> _loginState.value = ApiResult.Loading
                }
            }*/
        }
    }
    
    /**
     * Store additional session state data
     */
    private fun storeSessionState(email: String, name: String) {
        // Store login timestamp
        secureStorage.storeLong(StorageKeys.LAST_LOGIN_TIMESTAMP, System.currentTimeMillis())
        
        // Store user preferences if you have any
        secureStorage.storeString(StorageKeys.LAST_LOGGED_IN_EMAIL, email)
        secureStorage.storeString(StorageKeys.USER_NAME, name)

        val userState = UserState(
            isLoggedIn = true,
            name = name,
            email = email,
            userId = secureStorage.getString(StorageKeys.USER_ID)
        )
        secureStorage.storeObject(StorageKeys.USER_STATE, userState)
    }

    fun logout() {
        viewModelScope.launch {
            secureStorage.storeLong(StorageKeys.LAST_LOGOUT_TIMESTAMP, System.currentTimeMillis())
            
            logoutUseCase().collect { result ->
                _logoutEvent.value = true
                
                // Clear additional session state data
                clearSessionState()
                
                _uiEvent.emit(AuthUiEvent.LogoutSuccess)
            }
        }
    }
    
    /**
     * Clear all session state data
     */
    private fun clearSessionState() {

        secureStorage.remove(StorageKeys.USER_STATE)

        // secureStorage.clear()
    }

    fun resetLogoutEvent() {
        _logoutEvent.value = false
    }

    fun checkAuthStatus() {
        viewModelScope.launch {
            if (!isLoggedIn.value) {
                logout()
                _uiEvent.emit(AuthUiEvent.Unauthorized)
            }
        }
    }

    fun handleSessionExpiry() {
        viewModelScope.launch {
            logout()
            _uiEvent.emit(AuthUiEvent.SessionExpired)
        }
    }

    /**
     * Check if there's a previous session and restore it
     * This can be called during app startup to restore user state
     */
    fun restoreSession() {
        viewModelScope.launch {
            val userState = secureStorage.getObject<UserState>(StorageKeys.USER_STATE)
            val isLoggedIn = secureStorage.getBoolean(StorageKeys.IS_LOGGED_IN)
            
            if (userState != null && isLoggedIn) {

                sessionManager.isSessionValid().collect { isValid ->
                    if (!isValid) {
                        logout()
                        _uiEvent.emit(AuthUiEvent.SessionExpired)
                    }
                }
            }
        }
    }
    
    /**
     * Get last logged in user's email (useful for login form)
     */
    fun getLastLoggedInEmail(): String {
        return secureStorage.getString(StorageKeys.LAST_LOGGED_IN_EMAIL, "")
    }
    
    /**
     * Get time elapsed since last login
     * @return Time in milliseconds since last login, or null if not available
     */
    fun getTimeSinceLastLogin(): Long? {
        val lastLoginTime = secureStorage.getLong(StorageKeys.LAST_LOGIN_TIMESTAMP, 0L)
        return if (lastLoginTime > 0) {
            System.currentTimeMillis() - lastLoginTime
        } else null
    }

    /**
     * Get the current user state from secure storage
     * @return The UserState object or null if not available
     */
    fun getUserState(): UserState? {
        return secureStorage.getObject<UserState>(StorageKeys.USER_STATE)
    }

    sealed class AuthUiEvent {
        object LoginSuccess : AuthUiEvent()
        object LogoutSuccess : AuthUiEvent()
        object SessionExpired : AuthUiEvent()
        object Unauthorized : AuthUiEvent()
        data class Error(val message: String) : AuthUiEvent()
    }
}
