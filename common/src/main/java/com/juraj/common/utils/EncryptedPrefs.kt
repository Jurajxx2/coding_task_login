package com.juraj.common.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object EncryptedPrefs {
    private lateinit var encryptedPrefs: SharedPreferences

    fun initEncryptedPrefs(context: Context) {
        val key = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        encryptedPrefs =
            EncryptedSharedPreferences.create(
                context,
                "secret_shared_prefs",
                key,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
    }

    private const val AUTHORIZATION_HEADER_KEY = "authorization_header_key"

    val authorizationHeader
        get() = encryptedPrefs.getString(AUTHORIZATION_HEADER_KEY, null)

    fun setAuthorizationHeader(header: String) {
        encryptedPrefs.edit()
            .putString(AUTHORIZATION_HEADER_KEY, header)
            .apply()
    }

    fun clearAuthorizationHeader() {
        encryptedPrefs.edit()
            .clear()
            .apply()
    }
}