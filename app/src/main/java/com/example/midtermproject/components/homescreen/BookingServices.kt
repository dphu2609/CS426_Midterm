package com.example.midtermproject.components.homescreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.midtermproject.R

@Composable
fun BookingServices(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BookingServiceButton(
            text = "Trips",
            iconRes = R.drawable.trips_icon,
            modifier = Modifier.weight(1f)
        )
        BookingServiceButton(
            text = "Hotel",
            iconRes = R.drawable.hotel_icon,
            modifier = Modifier.weight(1f)
        )
        BookingServiceButton(
            text = "Transport",
            iconRes = R.drawable.transport_icon,
            modifier = Modifier.weight(1f)
        )
        BookingServiceButton(
            text = "Events",
            iconRes = R.drawable.events_icon,
            modifier = Modifier.weight(1f)
        )
    }
}
