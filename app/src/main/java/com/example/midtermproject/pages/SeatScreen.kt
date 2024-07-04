package com.example.midtermproject.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.midtermproject.components.FragmentHeader
import com.example.midtermproject.ui.theme.Palette1
import com.example.midtermproject.ui.theme.Palette4

@Composable
fun SeatScreen(navController: NavController) {
    var selectedNumber by remember { mutableStateOf(-1) }
    val numbers = (1..10).toList()

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
            Text(
                text = "Traveller",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.White
                )
            )
            LazyRow(
                modifier = Modifier.padding(10.dp)
            ) {
                items(numbers) { number ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .background(
                                if (number == selectedNumber) Palette1 else Color.Transparent,
                                shape = MaterialTheme.shapes.small
                            )
                            .clickable { selectedNumber = number }
                            .padding(16.dp)
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
    }
}
