package com.example.midtermproject.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.midtermproject.R
import com.example.midtermproject.components.FragmentHeader
import com.example.midtermproject.components.flightscreen.FlightDate
import com.example.midtermproject.components.flightscreen.FlightInfo
import com.example.midtermproject.ui.theme.Palette1
import com.example.midtermproject.ui.theme.Palette4
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FlightScreen(navController: NavController) {
    // State variable to hold the selected date
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var numOfFlight by remember { mutableStateOf(0) }

    var from by remember { mutableStateOf("Ho Chi Minh City") }
    var to by remember { mutableStateOf("Ha Noi") }

    var isFilterOpened by remember { mutableStateOf(false) }

    var sortedBy by remember { mutableStateOf("Price") }

    if (isFilterOpened) {
        FilterScreen(
            onClose = { isFilterOpened = it },
            onStartTime = {},
            onEndTime = {},
            onStartPrice = {},
            onEndPrice = {},
            onSortedBy = { sortedBy = it}
        )
    }
    else {
        Column {
            FragmentHeader(
                title = "Flights",
                onReturnClick = { navController.popBackStack() }
            )
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .background(Palette4, shape = MaterialTheme.shapes.medium)
            ) {
                // Pass the setter function to FlightDate
                FlightDate(
                    startDate = "2024-06-15",
                    endDate = "2024-06-30",
                    onDateSelected = { date ->
                        selectedDate = date
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "${numOfFlight} flights available",
                            color = Palette1
                        )
                    }
                    IconButton(
                        onClick = {isFilterOpened = true},
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(0.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.filter_icon),
                            contentDescription = "Swap",
                            tint = Palette1,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier.clickable { navController.navigate("seat") }
                ) {
                    selectedDate?.let { date ->
                        FlightInfo(
                            date = date,
                            from = from,
                            to = to,
                            departureAirportCode = "SGN",
                            arrivalAirportCode = "HAN",
                            onFlightReturned = {
                                numOfFlight = it
                            },
                            sortBy = sortedBy
                        )
                    }
                }
            }
        }
    }
}
