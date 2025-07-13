package com.nebuliton.pulsenerd.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.nebuliton.pulsenerd.api.LocalStats
import com.nebuliton.pulsenerd.api.WPAPIFetcher
import com.nebuliton.pulsenerd.api.WPAPIFormatter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GeekView(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var stats by remember { mutableStateOf<LocalStats?>(null) }
    var error by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val context = LocalContext.current

    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val columns = if (isPortrait) 2 else 3
    val rows = if (isPortrait) 3 else 2
    val totalCards = rows * columns

    // Daten laden
    LaunchedEffect(Unit) {
        while (true) {
            scope.launch {
                val result = WPAPIFetcher.fetchLocalStats(context)
                if (result != null) {
                    stats = result
                    error = false
                } else {
                    error = true
                }
            }
            delay(3000)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color.Black, Color(0xFF0D1A33))))
            .padding(16.dp)
    ) {
        when {
            error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("❌ Keine Verbindung zur API", color = Color.Red)
                }
            }

            stats == null -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }

            else -> {
                val items = listOf(
                    Triple("⌨️", "Keys", WPAPIFormatter.formatNumber(stats!!.keys)),
                    Triple("🖱", "Clicks", WPAPIFormatter.formatNumber(stats!!.clicks)),
                    Triple("⬇️", "Download", WPAPIFormatter.formatBytes(stats!!.downloadBytes)),
                    Triple("⬆️", "Upload", WPAPIFormatter.formatBytes(stats!!.uploadBytes)),
                    Triple("🌀", "Scrolls", WPAPIFormatter.formatNumber(stats!!.scrolls)),
                    Triple("🕖", "Uptime", WPAPIFormatter.formatSeconds(stats!!.uptime))
                ).take(totalCards)

                val cardSpacing = 12.dp
                val totalSpacing = cardSpacing * (rows - 1)

                BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                    val constraintsMaxHeight = this.maxHeight
                    val rowHeight = (constraintsMaxHeight - totalSpacing) / rows

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(cardSpacing),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        for (rowIndex in 0 until rows) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(rowHeight),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                for (colIndex in 0 until columns) {
                                    val index = rowIndex * columns + colIndex
                                    if (index < items.size) {
                                        val (icon, title, value) = items[index]
                                        StatCard(
                                            icon = icon,
                                            title = title,
                                            value = value,
                                            modifier = Modifier.weight(1f)
                                        )
                                    } else {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(icon: String, title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxSize(),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A2639))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val constraintsMaxHeight = this.maxHeight
                val heightPx = with(LocalDensity.current) { constraintsMaxHeight.toPx() }
                val scaleFactor = when {
                    heightPx < 180f -> 0.75f
                    heightPx < 240f -> 0.85f
                    else -> 1f
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        icon,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize * scaleFactor,
                        maxLines = 1,
                        softWrap = false
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        title.uppercase(),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = MaterialTheme.typography.labelMedium.fontSize * scaleFactor
                        ),
                        color = Color.Cyan,
                        maxLines = 1,
                        softWrap = false
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        value,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = MaterialTheme.typography.headlineSmall.fontSize * scaleFactor
                        ),
                        color = Color.White,
                        maxLines = 1,
                        softWrap = false
                    )
                }
            }
        }
    }
}