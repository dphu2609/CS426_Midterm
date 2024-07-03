package com.example.midtermproject.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.midtermproject.R

import com.example.midtermproject.components.homescreen.SearchBar
import com.example.midtermproject.components.homescreen.BookingServices

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController? = null) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Explore the beautiful world",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SearchBar(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
        )

        Text(
            text = "Booking Services",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        BookingServices(modifier = Modifier.fillMaxWidth(), navController = navController!!)
    }
}
