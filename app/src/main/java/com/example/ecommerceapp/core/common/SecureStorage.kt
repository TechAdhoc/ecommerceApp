package com.example.ecommerceapp.core.common

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import androidx.core.content.edit

/**
 * A secure storage solution using EncryptedSharedPreferences to store data securely.
 * It provides methods to store and retrieve different types of data (String, Boolean, Int, Object).
 */
@Singleton
class SecureStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val SECURE_STORAGE_FILE_NAME = "secure_storage_prefs"
    }

    private val masterKey = MasterKey.Builder(context)
        .setKeyGenParameterSpec(
            KeyGenParameterSpec.Builder(
                MasterKey.DEFAULT_MASTER_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .setUserAuthenticationRequired(false)
            .build()
        )
        .build()

    // Create the EncryptedSharedPreferences instance
    private val securePrefs = EncryptedSharedPreferences.create(
        context,
        SECURE_STORAGE_FILE_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    /**
     * Store a string value securely
     * @param key The key to store the value under
     * @param value The string value to store
     */
    fun storeString(key: String, value: String) {
        securePrefs.edit { putString(key, value) }
    }

    /**
     * Retrieve a string value
     * @param key The key to retrieve the value for
     * @param defaultValue The default value to return if the key doesn't exist
     * @return The stored string value or defaultValue if not found
     */
    fun getString(key: String, defaultValue: String = ""): String {
        return securePrefs.getString(key, defaultValue) ?: defaultValue
    }

    /**
     * Store a boolean value securely
     * @param key The key to store the value under
     * @param value The boolean value to store
     */
    fun storeBoolean(key: String, value: Boolean) {
        securePrefs.edit { putBoolean(key, value) }
    }

    /**
     * Retrieve a boolean value
     * @param key The key to retrieve the value for
     * @param defaultValue The default value to return if the key doesn't exist
     * @return The stored boolean value or defaultValue if not found
     */
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return securePrefs.getBoolean(key, defaultValue)
    }

    /**
     * Store an integer value securely
     * @param key The key to store the value under
     * @param value The integer value to store
     */
    fun storeInt(key: String, value: Int) {
        securePrefs.edit { putInt(key, value) }
    }

    /**
     * Retrieve an integer value
     * @param key The key to retrieve the value for
     * @param defaultValue The default value to return if the key doesn't exist
     * @return The stored integer value or defaultValue if not found
     */
    fun getInt(key: String, defaultValue: Int = 0): Int {
        return securePrefs.getInt(key, defaultValue)
    }

    /**
     * Store a long value securely
     * @param key The key to store the value under
     * @param value The long value to store
     */
    fun storeLong(key: String, value: Long) {
        securePrefs.edit { putLong(key, value) }
    }

    /**
     * Retrieve a long value
     * @param key The key to retrieve the value for
     * @param defaultValue The default value to return if the key doesn't exist
     * @return The stored long value or defaultValue if not found
     */
    fun getLong(key: String, defaultValue: Long = 0L): Long {
        return securePrefs.getLong(key, defaultValue)
    }

    /**
     * Store a float value securely
     * @param key The key to store the value under
     * @param value The float value to store
     */
    fun storeFloat(key: String, value: Float) {
        securePrefs.edit { putFloat(key, value) }
    }

    /**
     * Retrieve a float value
     * @param key The key to retrieve the value for
     * @param defaultValue The default value to return if the key doesn't exist
     * @return The stored float value or defaultValue if not found
     */
    fun getFloat(key: String, defaultValue: Float = 0f): Float {
        return securePrefs.getFloat(key, defaultValue)
    }

    /**
     * Store an object securely by serializing it to JSON
     * @param key The key to store the value under
     * @param value The object to store
     */
    inline fun <reified T> storeObject(key: String, value: T) {
        val jsonString = Json.encodeToString(value)
        storeString(key, jsonString)
    }

    /**
     * Retrieve an object by deserializing from stored JSON
     * @param key The key to retrieve the value for
     * @return The deserialized object or null if not found or if deserialization fails
     */
    inline fun <reified T> getObject(key: String): T? {
        val jsonString = getString(key)
        return try {
            if (jsonString.isNotEmpty()) {
                Json.decodeFromString<T>(jsonString)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Check if a key exists in secure storage
     * @param key The key to check
     * @return true if the key exists, false otherwise
     */
    fun contains(key: String): Boolean {
        return securePrefs.contains(key)
    }

    /**
     * Remove a value from secure storage
     * @param key The key to remove
     */
    fun remove(key: String) {
        securePrefs.edit { remove(key) }
    }

    /**
     * Clear all values from secure storage
     */
    fun clear() {
        securePrefs.edit { clear() }
    }
}
