package com.example.midtermproject.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.midtermproject.R
import com.example.midtermproject.components.bookingscreen.BookOption


@Composable
fun BookingScreen(modifier : Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookOption(
            title = "Hotel",
            imageRes = R.drawable.hotel_image
        )
        BookOption(
            title = "Transport",
            imageRes = R.drawable.transport_image
        )
    }
}