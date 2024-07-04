package com.example.midtermproject.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.midtermproject.components.FragmentHeader
import com.example.midtermproject.components.transportscreen.LocationForm
import java.time.LocalTime


@Composable
fun TransportScreen(
    navController: NavController
) {
    Column {
        FragmentHeader(
            title = "Transport Booking",
            onReturnClick = {navController.popBackStack()}
        )
        LocationForm(navController)
    }
}


