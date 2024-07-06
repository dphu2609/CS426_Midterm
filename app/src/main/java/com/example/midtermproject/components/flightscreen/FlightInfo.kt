package com.example.midtermproject.components.flightscreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.midtermproject.R
import com.example.midtermproject.ui.theme.Palette1
import com.example.midtermproject.ui.theme.Palette3
import java.time.LocalDate
import java.time.LocalTime
import kotlin.random.Random

data class Flight(
    val date: LocalDate,
    val from: String,
    val to: String,
    val time: LocalTime,
    val price: Double,
    val flightCode: String,
    val departureAirportCode: String,
    val arrivalAirportCode: String
)

@RequiresApi(Build.VERSION_CODES.O)
fun generateRandomFlights(date: LocalDate, from: String, to: String, departureAirportCode: String, arrivalAirportCode: String): List<Flight> {
    val flights = mutableListOf<Flight>()
    val numFlights = Random.nextInt(5, 8)  // Random number of flights between 5 and 8
    for (i in 1..numFlights) {
        val hour = Random.nextInt(0, 24)
        val minute = Random.nextInt(0, 6) * 10  // minutes are multiples of 10
        val time = LocalTime.of(hour, minute)
        val price = Random.nextInt(100, 500)
        val flightCode = "FL${Random.nextInt(100, 999)}"
        flights.add(Flight(date, from, to, time, price.toDouble(), flightCode, departureAirportCode, arrivalAirportCode))
    }
    return flights
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FlightInfo(
    date: LocalDate,
    from: String,
    to: String,
    departureAirportCode: String,
    arrivalAirportCode: String,
    sortBy: String? = null,
    departureTime: String? = "all",
    startPrice: Double? = null,
    endPrice: Double? = null,
    onFlightReturned: (Int) -> Unit
) {
    val flights = generateRandomFlights(date, from, to, departureAirportCode, arrivalAirportCode)
        .filter { flight ->
            (departureTime == "day" && (flight.time >= LocalTime.of(6, 0) && flight.time <= LocalTime.of(18, 0))) ||
                    (departureTime == "night" && (flight.time < LocalTime.of(6, 0) || flight.time > LocalTime.of(18, 0))) ||
                    (departureTime == "all")
        }
        .filter { flight ->
            (startPrice == null || flight.price >= startPrice) &&
                    (endPrice == null || flight.price <= endPrice)
        }
        .sortedWith(
            when (sortBy) {
                "Price" -> compareBy { it.price }
                "Arrival Time", "Departure Time" -> compareBy { it.time }
                else -> compareBy { it.price }
            }
        )

    onFlightReturned(flights.size)

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(flights.size) { index ->
            val flight = flights[index]
            FlightInfoItem(
                date = flight.date,
                from = flight.from,
                to = flight.to,
                time = flight.time,
                price = flight.price,
                flightCode = flight.flightCode,
                departureAirportCode = flight.departureAirportCode,
                arrivalAirportCode = flight.arrivalAirportCode
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun FlightInfoItem(
    date: LocalDate? = null,
    from: String,
    to: String,
    time: LocalTime? = null,
    price: Double? = null,
    flightCode: String? = null,
    departureAirportCode: String,
    arrivalAirportCode: String
) {
    val textColor = Color.Black // Update this with your actual Palette1 color definition
    val textSize = 14.sp // Update this value to your desired text size
    val locationTextSize = 18.sp
    val notationTextColor = Palette1

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(color=Palette3)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = departureAirportCode,
                        color = textColor,
                        fontSize = textSize
                    )
                    Text(
                        text = from,
                        color = textColor,
                        fontSize = locationTextSize
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "Return to previous screen",
                modifier = Modifier
                    .size(32.dp)
            )
            Column(
                modifier = Modifier.weight(1f).padding(start = 56.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = arrivalAirportCode,
                        color = textColor,
                        fontSize = textSize
                    )
                    Text(
                        text = to,
                        color = textColor,
                        fontSize = locationTextSize
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (date != null) {
                Text(
                    text = date.toString(),
                    color = textColor,
                    fontSize = textSize
                )
            }
            if (time != null) {
                Text(
                    text = time.toString(),
                    color = textColor,
                    fontSize = textSize
                )
            }
            if (price != null) {
                Text(
                    text = price.toString(),
                    color = textColor,
                    fontSize = textSize
                )
            }
            if (flightCode != null) {
                Text(
                    text = flightCode,
                    color = textColor,
                    fontSize = textSize
                )
            }
        }
    }
}

