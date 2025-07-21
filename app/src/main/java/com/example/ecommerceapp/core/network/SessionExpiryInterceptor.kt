package com.example.ecommerceapp.core.network

import com.example.ecommerceapp.core.session.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

/**
 * Interceptor to check if the API response indicates token expiry or unauthorized session
 * Handles the logout logic in case of unauthorized response
 */
class SessionExpiryInterceptor @Inject constructor(
    private val sessionManager: SessionManager
) : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        
        // Check if response code is 401 Unauthorized
        if (response.code == 401) {
            // Handle session expiry - logout the user
            CoroutineScope(Dispatchers.IO).launch {
                sessionManager.logout()
            }
            
            throw IOException("Session expired. Please login again.")
        }
        
        return response
    }
}
