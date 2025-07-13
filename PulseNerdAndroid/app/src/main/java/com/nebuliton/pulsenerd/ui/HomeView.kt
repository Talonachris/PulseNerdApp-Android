package com.nebuliton.pulsenerd.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Cable
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.ScreenRotation
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nebuliton.pulsenerd.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(Color(0xFF050A12), Color(0xFF00121F))
                )
            )
            .padding(0.dp)
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(16.dp))

            Box(contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .background(Color.Cyan.copy(alpha = 0.1f))
                        .blur(8.dp)
                )
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color.Transparent)
                        .border(4.dp, Color.Cyan.copy(alpha = 0.4f), CircleShape)
                        .shadow(10.dp, CircleShape)
                )
                Image(
                    painter = painterResource(id = R.drawable.pulseflash_logo),
                    contentDescription = "PulseFlash Logo",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .shadow(5.dp, RoundedCornerShape(14.dp))
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("Welcome to", fontSize = 18.sp, color = Color.Cyan)
            Text(
                "PulseNerd",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Live view for your WhatPulse stats – optimized for landscape.",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 16.dp),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "⚡️ Connect to your local WhatPulse client and enjoy real-time visual feedback.",
                color = Color.Cyan,
                fontSize = 16.sp,
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(12.dp))
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            Divider(
                color = Color.White.copy(alpha = 0.2f),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                FeatureRow(
                    "Real-Time Charts",
                    "Clicks, keys and bandwidth – updated every second.",
                    Icons.Filled.QueryStats
                )
                FeatureRow(
                    "Pulse Button",
                    "Instantly trigger a new pulse right from the app.",
                    Icons.Filled.Bolt
                )
                FeatureRow(
                    "Designed for Landscape",
                    "Perfect for desks, streamers and horizontal docks.",
                    Icons.Filled.ScreenRotation
                )
                FeatureRow(
                    "Runs Locally",
                    "Connects to the local WhatPulse client API.",
                    Icons.Filled.Cable
                )
                FeatureRow(
                    "Modular Code",
                    "Jetpack Compose-based and iOS-ready.",
                    Icons.Filled.Code
                )
                FeatureRow(
                    "Made with ❤️",
                    "A geek window for power users – from Nebuliton.",
                    Icons.Filled.Favorite
                )
            }

            Divider(
                color = Color.White.copy(alpha = 0.1f),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text("🔗 Links", color = Color.Cyan, fontSize = 18.sp)

            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                LinkLogo(R.drawable.nebuliton_logo, "Nebuliton")
                LinkLogo(R.drawable.whatpulse_icon, "WhatPulse")
            }

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun FeatureRow(title: String, subtitle: String, icon: ImageVector) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Cyan,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(subtitle, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun LinkLogo(imageId: Int, name: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = name,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp))
                .shadow(8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(name, fontSize = 12.sp, color = Color.Cyan)
    }
}