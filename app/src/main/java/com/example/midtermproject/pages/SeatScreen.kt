package com.example.midtermproject.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.midtermproject.components.FragmentHeader
import com.example.midtermproject.ui.theme.Palette1
import com.example.midtermproject.ui.theme.Palette3
import com.example.midtermproject.ui.theme.Palette4
import com.example.midtermproject.ui.theme.Palette5
import kotlin.random.Random

data class SeatStruct(
    var row: Int,
    var column: Char,
    var status: String
)

fun generateRandomSeats(): List<SeatStruct> {
    val seats = mutableListOf<SeatStruct>()
    for (row in 1..30) {
        for (column in 'A'..'D') {
            val status = Random.nextInt(0, 2).let {
                when (it) {
                    0 -> "Available"
                    else -> "Booked"
                }
            }
            seats.add(SeatStruct(row, column, status))
        }
    }
    return seats
}

@Composable
fun SeatScreen(navController: NavController) {
    var seats by remember { mutableStateOf(generateRandomSeats()) }
    var numOfSeats by remember { mutableStateOf(0) }
    var selectedSeats by remember { mutableStateOf(listOf<SeatStruct>()) }

    val priceEach = 100.0

    Column {
        FragmentHeader(
            title = "Select Seats",
            onReturnClick = { navController.popBackStack() }
        )
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .background(Palette4, shape = MaterialTheme.shapes.medium)
        ) {
            SeatTraveler(
                onNumOfSeatsSelected = { numOfSeats = it }
            )

            SeatNotation()

            SeatMap(
                seats = seats,
                numOfSelectedSeats = numOfSeats
            ) { seatId ->
                updateSeatStatus(seatId, seats) { updatedSeats, selectedSeatsList ->
                    seats = updatedSeats
                    selectedSeats = selectedSeatsList
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Palette3, shape = MaterialTheme.shapes.medium)

            ) {
                SelectedSeatsSection(
                    selectedSeats = selectedSeats,
                    totalPrice = selectedSeats.size * priceEach
                )
                Button(
                    onClick = { navController.navigate("boardingpass") },
                    colors = ButtonDefaults.buttonColors(containerColor = Palette1),
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Text(
                        text = "Continue",
                        color = Color.White,
                        fontSize = 20.sp,
                    )
                }
            }
        }
    }
}

fun updateSeatStatus(seatId: String, seats: List<SeatStruct>, updateSeats: (List<SeatStruct>, List<SeatStruct>) -> Unit) {
    val updatedSeats = seats.map { seat ->
        if ("${seat.row}${seat.column}" == seatId) {
            seat.copy(status = if (seat.status == "Available") "Selected" else if (seat.status == "Selected") "Available" else seat.status)
        } else {
            seat
        }
    }
    val selectedSeats = updatedSeats.filter { it.status == "Selected" }
    updateSeats(updatedSeats, selectedSeats)
}

@Composable
fun SeatMap(seats: List<SeatStruct>, numOfSelectedSeats: Int, onSeatClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .height(325.dp)
    ) {
        for (row in 1..(seats.size / 4)) {
            item {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        for (column in 'A'..'B') {
                            val seat = seats[(row - 1) * 4 + (column - 'A')]
                            SeatItem(
                                seat = "${seat.row}${seat.column}",
                                isSelected = seat.status,
                                onClick = { onSeatClick("${seat.row}${seat.column}") }
                            )
                        }
                    }
                    Text(
                        text = "$row",
                        color = Color.White,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Row {
                        for (column in 'C'..'D') {
                            val seat = seats[(row - 1) * 4 + (column - 'A')]
                            SeatItem(
                                seat = "${seat.row}${seat.column}",
                                isSelected = seat.status,
                                onClick = { onSeatClick("${seat.row}${seat.column}") }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SeatItem(seat: String, isSelected: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(50.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                when (isSelected) {
                    "Selected" -> Palette5
                    "Booked" -> Palette1
                    else -> Palette3
                }
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = seat,
            color = when (isSelected) {
                "Available" -> Color.Black
                else -> Color.White
            },
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun SelectedSeatsSection(selectedSeats: List<SeatStruct>, totalPrice: Double) {
    val selectedSeatsText = selectedSeats.joinToString(", ") { "${it.row}${it.column}" }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(Color.Transparent, shape = MaterialTheme.shapes.medium)
    ) {
        Text("Selected Seats: $selectedSeatsText", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(10.dp))
        Text("Total Price: $$totalPrice", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
    }
}


@Composable
fun SeatTraveler(onNumOfSeatsSelected: (Int) -> Unit) {
    var selectedNumber by remember { mutableStateOf(-1) }
    val numbers = (1..50).toList()

    Text(
        text = "Traveller",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(bottom = 6.dp)
            .padding(horizontal = 8.dp)
    )
    LazyRow(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        items(numbers) { number ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .background(
                        if (number == selectedNumber) Palette1 else Color.Transparent,
                        shape = MaterialTheme.shapes.small
                    )
                    .clickable {
                        selectedNumber = number
                        onNumOfSeatsSelected(number)
                    }
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 12.dp)
            ) {
                Text(
                    text = number.toString(),
                    color = Color.White,
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
    }
}

@Composable
fun SeatNotation() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Palette5)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Selected",
                color = Color.White,
                style = TextStyle(fontSize = 16.sp)
            )
        }
        Row() {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Palette1)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Booked",
                color = Color.White,
                style = TextStyle(fontSize = 16.sp)
            )
        }
        Row() {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Palette3)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Available",
                color = Color.White,
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}