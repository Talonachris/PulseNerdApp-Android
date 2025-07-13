package com.nebuliton.pulsenerd.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

object SettingsDataStore {
    private val KEY_IP = stringPreferencesKey("ip_address")
    private val KEY_PORT = stringPreferencesKey("port")

    suspend fun saveIPAndPort(context: Context, ip: String, port: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_IP] = ip
            prefs[KEY_PORT] = port
        }
    }

    suspend fun getIPAndPort(context: Context): Pair<String, String> {
        val prefs = context.dataStore.data.first()
        val ip = prefs[KEY_IP] ?: "127.0.0.1"
        val port = prefs[KEY_PORT] ?: "3490"
        return Pair(ip, port)
    }
}