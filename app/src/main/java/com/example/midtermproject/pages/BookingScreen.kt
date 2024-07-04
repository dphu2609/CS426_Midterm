package com.example.midtermproject.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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

data class BookingOption(
    val title: String,
    val imageRes: Int,
    val onClick: () -> Unit
)

@Composable
fun BookingScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Booking",
            style = TextStyle(
                fontSize = 32.sp,
                color = Color.Black
            )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val bookingOptions = listOf(
                BookingOption(
                    title = "Hotel",
                    imageRes = R.drawable.hotel_image,
                    onClick = {}
                ),
                BookingOption(
                    title = "Transport",
                    imageRes = R.drawable.transport_image,
                    onClick = { navController.navigate("transport") }
                ),
                BookingOption(
                    title = "Trip",
                    imageRes = R.drawable.trip_image,
                    onClick = {}
                ),
                BookingOption(
                    title = "Event",
                    imageRes = R.drawable.event_image,
                    onClick = {}
                )

            )
            items(bookingOptions.size) { index ->
                val option = bookingOptions[index]
                BookOption(
                    title = option.title,
                    imageRes = option.imageRes,
                    onClick = option.onClick
                )
            }
        }
    }
}
