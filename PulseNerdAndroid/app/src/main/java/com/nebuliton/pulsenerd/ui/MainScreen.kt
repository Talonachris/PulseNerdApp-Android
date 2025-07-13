package com.nebuliton.pulsenerd.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.nebuliton.pulsenerd.ui.GeekView
import com.nebuliton.pulsenerd.ui.HomeView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var currentTab by remember { mutableStateOf("home") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PulseNerd", color = Color.White) },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("settings")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0A1423),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF0A1423)) {
                NavigationBarItem(
                    selected = currentTab == "home",
                    onClick = {
                        currentTab = "home"
                        navController.navigate("home") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                    label = { Text("Home") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Cyan,
                        selectedTextColor = Color.Cyan,
                        unselectedIconColor = Color.Cyan.copy(alpha = 0.5f),
                        unselectedTextColor = Color.Cyan.copy(alpha = 0.5f)
                    )
                )
                NavigationBarItem(
                    selected = currentTab == "geek",
                    onClick = {
                        currentTab = "geek"
                        navController.navigate("geek") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Filled.Computer, contentDescription = null) },
                    label = { Text("Geek") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Cyan,
                        selectedTextColor = Color.Cyan,
                        unselectedIconColor = Color.Cyan.copy(alpha = 0.5f),
                        unselectedTextColor = Color.Cyan.copy(alpha = 0.5f)
                    )
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeView() }
            composable("geek") { GeekView() }
            composable("settings") {
                SettingsView(onBack = { navController.popBackStack() })
            }
        }
    }
}