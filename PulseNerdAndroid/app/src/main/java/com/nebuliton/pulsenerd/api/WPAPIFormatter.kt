package com.nebuliton.pulsenerd.api

import java.text.DecimalFormat
import kotlin.math.log10
import kotlin.math.pow

object WPAPIFormatter {

    private val decimalFormat = DecimalFormat("#,###")

    fun formatBytes(bytes: Long): String {
        if (bytes < 1024) return "$bytes B"

        val exp = (log10(bytes.toDouble()) / log10(1024.0)).toInt()
        val prefix = "KMGTPE"[exp - 1]
        val result = bytes / 1024.0.pow(exp.toDouble())

        return String.format("%.1f %sB", result, prefix)
    }

    fun formatNumber(value: Long): String {
        return decimalFormat.format(value)
    }

    fun formatSeconds(seconds: Long): String {
        val h = seconds / 3600
        val m = (seconds % 3600) / 60
        val s = seconds % 60

        return when {
            h > 0 -> String.format("%dh %02dm", h, m)
            m > 0 -> String.format("%dm %02ds", m, s)
            else -> "$s s"
        }
    }
}