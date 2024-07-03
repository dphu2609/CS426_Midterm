package com.example.midtermproject.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.midtermproject.R
import com.example.midtermproject.components.bookingscreen.BookOption
import com.example.midtermproject.ui.theme.Palette4


@Composable
fun BookingScreen(modifier : Modifier = Modifier, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Booking",
            style = TextStyle(
                fontSize = 32.sp,
                color = Color.Black
            )
        )
        BookOption(
            title = "Hotel",
            imageRes = R.drawable.hotel_image,
            onClick = {}
        )
        BookOption(
            title = "Transport",
            imageRes = R.drawable.transport_image,
            onClick = { navController.navigate("transport") }
        )
    }
}