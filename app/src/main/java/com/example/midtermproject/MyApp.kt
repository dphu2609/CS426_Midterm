package com.example.midtermproject

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.midtermproject.pages.BookingScreen
import com.example.midtermproject.pages.HomeScreen
import com.example.midtermproject.ui.theme.Palette2
import com.example.midtermproject.ui.theme.Palette3
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val items = listOf("Home", "Booking", "Notification", "Account")
    val icons = listOf(
        painterResource(id = R.drawable.home_icon),
        painterResource(id = R.drawable.ticket_icon),
        painterResource(id = R.drawable.notification_icon),
        painterResource(id = R.drawable.account_icon)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(containerColor = Palette3) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                NavigationBar(
                    containerColor = Palette3,
                    contentColor = Color.Black
                ) {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(painter = icons[index], contentDescription = item) },
                            label = { Text(item) },
                            colors = NavigationBarItemDefaults.colors(indicatorColor = Palette2),
                            selected = currentRoute == item.lowercase(),
                            onClick = {
                                navController.navigate(item.lowercase()) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen() }
            composable("booking") { BookingScreen() }
            composable("notification") { NotificationScreen() }
            composable("account") { AccountScreen() }
        }
    }
}

@Composable
fun NotificationScreen() {
    // Implement your Notification screen here
    Text("Notification Screen")
}

@Composable
fun AccountScreen() {
    // Implement your Account screen here
    Text("Account Screen")
}
