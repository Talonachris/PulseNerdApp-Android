package com.nebuliton.pulsenerd.api

import kotlinx.serialization.Serializable

@Serializable
data class LocalStats(
    val keys: Long,
    val clicks: Long,
    val downloadBytes: Long,
    val uploadBytes: Long,
    val uptime: Long,
    val scrolls: Long
)

@Serializable
data class UnpulsedStats(
    val keys: Long = 0,
    val clicks: Long = 0,
    val download: Long = 0,
    val upload: Long = 0,
    val uptime: Long = 0
)