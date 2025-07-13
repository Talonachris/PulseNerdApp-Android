package com.nebuliton.pulsenerd.api

import android.content.Context
import com.nebuliton.pulsenerd.settings.SettingsDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object WPAPIFetcher {

    suspend fun fetchLocalStats(context: Context): LocalStats? = withContext(Dispatchers.IO) {
        try {
            val (ip, port) = SettingsDataStore.getIPAndPort(context)
            val url = URL("http://$ip:$port/v1/all-stats")
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 5000
            connection.readTimeout = 5000
            connection.requestMethod = "GET"
            connection.connect()

            if (connection.responseCode == 200) {
                val response = connection.inputStream.bufferedReader().readText()
                val json = JSONObject(response)

                return@withContext LocalStats(
                    keys = json.getJSONObject("unpulsed").getLong("keys"),
                    clicks = json.getJSONObject("unpulsed").getLong("clicks"),
                    downloadBytes = json.getJSONObject("unpulsed").getLong("download"),
                    uploadBytes = json.getJSONObject("unpulsed").getLong("upload"),
                    uptime = json.getJSONObject("unpulsed").getLong("uptime"),
                    scrolls = json.getJSONObject("unpulsed").getLong("scrolls")
                )
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}