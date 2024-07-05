package com.example.midtermproject.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.midtermproject.R
import com.example.midtermproject.components.FragmentHeader
import com.example.midtermproject.components.flightscreen.FlightInfoItem
import com.example.midtermproject.ui.theme.Palette1
import com.example.midtermproject.ui.theme.Palette3
import com.example.midtermproject.ui.theme.Palette4
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BoardingPassScreen(navController: NavController) {

    val date = LocalDate.of(2024, 12, 12)

    val time = LocalTime.of(12, 30)

    val passenger = "1 Adult"

    val ticketCode = "FL1234"

    val typeClass = "Business"

    val seat = "2A"

    val barCode = "A3427371903848"

    Column {
        FragmentHeader(
            title = "Flights",
            onReturnClick = { navController.popBackStack() }
        )
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .background(Palette4, shape = MaterialTheme.shapes.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(id = R.drawable.boardingpass),
                contentDescription = "Swap",
                tint = Palette1,
                modifier = Modifier.size(180.dp).padding(20.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FlightInfoItem(
                    from = "Ho Chi Minh",
                    to = "Ha Noi",
                    departureAirportCode = "SGN",
                    arrivalAirportCode = "HAN",
                    date = date,
                    time = time,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(
                            text = "Passenger",
                            color = Palette1,
                            fontSize = 10.sp
                        )
                        Text(
                            text = passenger,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                    Column() {
                        Text(
                            text = "Ticket Code",
                            color = Palette1,
                            fontSize = 10.sp
                        )
                        Text(
                            text = ticketCode,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                    Column() {
                        Text(
                            text = "Class",
                            color = Palette1,
                            fontSize = 10.sp
                        )
                        Text(
                            text = typeClass,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                    Column() {
                        Text(
                            text = "Seat",
                            color = Palette1,
                            fontSize = 10.sp
                        )
                        Text(
                            text = seat,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .background(Palette3, shape = MaterialTheme.shapes.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.barcode_image),
                    contentDescription = "Barcode",
                    modifier = Modifier
                        .width(280.dp)
                        .padding(16.dp)
                )
            }

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Palette1),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)

            ) {
                Text(
                    text = "Download Ticket",
                    color = Color.White,
                    fontSize = 20.sp,
                )
            }

        }
    }
}