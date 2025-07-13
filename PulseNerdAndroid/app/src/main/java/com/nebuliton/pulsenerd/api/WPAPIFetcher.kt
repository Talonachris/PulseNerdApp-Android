package com.nebuliton.pulsenerd.api

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.longOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import com.nebuliton.pulsenerd.api.LocalStats

// Datenmodell für die entpackten Werte aus "unpulsed"

object WPAPIFetcher {

    private val client = OkHttpClient()

    suspend fun fetchLocalStats(ip: String): LocalStats? {
        val url = "http://$ip:3490/v1/all-stats"
        val request = Request.Builder().url(url).build()

        return withContext(Dispatchers.IO) {
            try {
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        Log.e("WPAPIFetcher", "❌ API call failed: ${response.code}")
                        return@withContext null
                    }

                    val body = response.body?.string() ?: run {
                        Log.e("WPAPIFetcher", "❌ Response body is null")
                        return@withContext null
                    }

                    val jsonObject = Json.parseToJsonElement(body).jsonObject
                    val unpulsed = jsonObject["unpulsed"]?.jsonObject ?: run {
                        Log.e("WPAPIFetcher", "❌ 'unpulsed' field missing in response")
                        return@withContext null
                    }

                    val stats = LocalStats(
                        keys = unpulsed["keys"]?.jsonPrimitive?.longOrNull ?: 0,
                        clicks = unpulsed["clicks"]?.jsonPrimitive?.longOrNull ?: 0,
                        downloadBytes = unpulsed["download"]?.jsonPrimitive?.longOrNull ?: 0,
                        uploadBytes = unpulsed["upload"]?.jsonPrimitive?.longOrNull ?: 0,
                        uptime = unpulsed["uptime"]?.jsonPrimitive?.longOrNull ?: 0,
                        scrolls = unpulsed["scrolls"]?.jsonPrimitive?.longOrNull ?: 0
                    )

                    Log.d("WPAPIFetcher", "✅ Stats loaded: $stats")
                    return@withContext stats
                }
            } catch (e: IOException) {
                Log.e("WPAPIFetcher", "❌ Network error: ${e.localizedMessage}")
                return@withContext null
            }
        }
    }
}