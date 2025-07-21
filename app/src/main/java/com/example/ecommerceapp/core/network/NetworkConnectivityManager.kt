package com.example.ecommerceapp.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manager class that handles network connectivity monitoring
 */
@Singleton
class NetworkConnectivityManager @Inject constructor(
    private val context: Context
) {
    private val connectivityManager = 
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    
    /**
     * Observe network connectivity as a Flow
     * @return Flow<Boolean> - true if connected, false otherwise
     */
    fun observeConnectivity(): Flow<Boolean> = callbackFlow {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                launch { send(true) }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                launch { send(false) }
            }
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, callback)

        // Set current state initially
        val isConnected = isCurrentlyConnected()
        send(isConnected)

        // Remove callback when Flow collection ends
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.distinctUntilChanged()

    /**
     * Check if the device is currently connected to the internet
     * @return Boolean - true if connected, false otherwise
     */
    fun isCurrentlyConnected(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}

/**
 * Composable to observe network connectivity state
 * @return State<Boolean> - true if connected, false otherwise
 */
@Composable
fun NetworkConnectivityManager.observeConnectivityAsState(): State<Boolean> {
    return produceState(initialValue = isCurrentlyConnected()) {
        observeConnectivity().collect { value = it }
    }
}
