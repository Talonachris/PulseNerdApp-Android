package com.nebuliton.pulsenerd.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.nebuliton.pulsenerd.settings.SettingsDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsView(onBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var ip by remember { mutableStateOf("") }
    var port by remember { mutableStateOf("") }
    var showSaved by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val (savedIp, savedPort) = SettingsDataStore.getIPAndPort(context)
        ip = savedIp
        port = savedPort
    }

    Scaffold(
        containerColor = Color.Transparent,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF050A12), Color(0xFF00121F))
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Text("Connection Settings", color = Color.Cyan, style = MaterialTheme.typography.titleMedium)

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = ip,
                        onValueChange = { ip = it },
                        label = { Text("IP Address") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedLabelColor = Color.Cyan,
                            unfocusedLabelColor = Color.Gray
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = port,
                        onValueChange = { port = it },
                        label = { Text("Port") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedLabelColor = Color.Cyan,
                            unfocusedLabelColor = Color.Gray
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            scope.launch {
                                SettingsDataStore.saveIPAndPort(context, ip, port)
                                showSaved = true
                                delay(2000)
                                showSaved = false
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan.copy(alpha = 0.2f))
                    ) {
                        Text("Save Settings", color = Color.Cyan)
                    }

                    AnimatedVisibility(visible = showSaved) {
                        Text(
                            text = "✅ Saved!",
                            color = Color.Green,
                            modifier = Modifier.padding(top = 12.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // TODO Buttons (noch nicht verlinkt)
                    SettingButton("About PulseNerd") {}
                    SettingButton("Our Other Apps") {}
                    SettingButton("Usage Tips") {}

                    SettingButton("Privacy Policy") {
                        val url = "https://pulseview.nebuliton.de/pulseflash/flash_legal.html"
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    }

                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    )
}

@Composable
fun SettingButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color.Cyan.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(text, color = Color.Cyan, style = MaterialTheme.typography.bodyMedium)
    }
}