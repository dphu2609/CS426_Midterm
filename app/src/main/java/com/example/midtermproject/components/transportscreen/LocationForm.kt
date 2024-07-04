package com.example.midtermproject.components.transportscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.midtermproject.R
import com.example.midtermproject.ui.theme.Palette1
import com.example.midtermproject.ui.theme.Palette4

fun swapLocations(from: String, to: String): Pair<String, String> {
    return Pair(to, from)
}

data class LocationField(
    val state: MutableState<String>,
    val icon: Int
)

@Composable
fun LocationForm(navController: NavController) {
    var fromText by remember { mutableStateOf("") }
    var toText by remember { mutableStateOf("") }
    var departureDate by remember { mutableStateOf("") }
    var returnDate by remember { mutableStateOf("") }

    var passengerNumber by remember { mutableStateOf("") }
    var kidsNumber by remember { mutableStateOf("") }
    var petsNumber by remember { mutableStateOf("") }
    var luggageNumber by remember { mutableStateOf("") }


    val fields = listOf(
        LocationField(remember { mutableStateOf(passengerNumber) }, R.drawable.adult_icon),
        LocationField(remember { mutableStateOf(kidsNumber) }, R.drawable.kid_icon),
        LocationField(remember { mutableStateOf(petsNumber) }, R.drawable.pet_icon),
        LocationField(remember { mutableStateOf(luggageNumber) }, R.drawable.luggage_icon)
    )

    var isBusinessClassSelected by remember { mutableStateOf(false) }

    var isTransportTypeSelected by remember { mutableStateOf("") }

    val transportIcons = listOf(
        R.drawable.transport_icon,
        R.drawable.boat_icon,
        R.drawable.bus_icon,
        R.drawable.train_icon
    )


    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .background(Palette4, shape = MaterialTheme.shapes.medium)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally)
        ) {
            LocationInputField(
                text = fromText,
                onTextChange = { fromText = it },
                placeholder = "From",
                icon = Icons.Default.LocationOn,
                modifier = Modifier.widthIn(max = 270.dp) // Adjusted to take available space
            )
            IconButton(
                onClick = {
                    val (newFrom, newTo) = swapLocations(fromText, toText)
                    fromText = newFrom
                    toText = newTo
                },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(0.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.swap_icon),
                    contentDescription = "Swap",
                    tint = Palette1,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LocationInputField(
            text = toText,
            onTextChange = { toText = it },
            placeholder = "To",
            icon = Icons.Default.LocationOn,
            modifier = Modifier
                .widthIn(max = 320.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Departure",
                    style = TextStyle(fontSize = 16.sp, color = Palette1),
                    modifier = Modifier.padding(8.dp)
                )
                LocationInputField(
                    text = departureDate,
                    onTextChange = { departureDate = it },
                    placeholder = "YYYY-MM-DD",
                    modifier = Modifier
                        .widthIn(max = 140.dp)
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Return",
                    style = TextStyle(fontSize = 16.sp, color = Palette1),
                    modifier = Modifier.padding(8.dp)
                )
                LocationInputField(
                    text = returnDate,
                    onTextChange = { returnDate = it },
                    placeholder = "YYYY-MM-DD",
                    modifier = Modifier
                        .widthIn(max = 145.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Passenger & Luggage",
            style = TextStyle(fontSize = 16.sp, color = Palette1),
            modifier = Modifier.padding(start = 18.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally)
        ) {
            fields.forEach { field ->
                LocationInputField(
                    text = field.state.value,
                    onTextChange = { field.state.value = it },
                    placeholder = "",
                    painter = field.icon,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 10.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Class",
            style = TextStyle(fontSize = 16.sp, color = Palette1),
            modifier = Modifier.padding(start = 18.dp, bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start)
        ) {
            Button(
                onClick = {isBusinessClassSelected = !isBusinessClassSelected},
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isBusinessClassSelected) Palette1 else Color.White,
                    contentColor = if (isBusinessClassSelected) Color.White else Palette1
                )
            ) {
                Text(
                    text = "Economy",
                    style = TextStyle(fontSize = 16.sp, color = if (isBusinessClassSelected) Color.White else Palette1),
                    modifier = Modifier.padding(8.dp)
                )
            }
            Button(
                onClick = {isBusinessClassSelected = !isBusinessClassSelected},
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isBusinessClassSelected) Color.White else Palette1,
                    contentColor = if (isBusinessClassSelected) Palette1 else Color.White
                )
            ) {
                Text(
                    text = "Business",
                    style = TextStyle(fontSize = 16.sp, color = if (isBusinessClassSelected) Palette1 else Color.White),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Transport",
            style = TextStyle(fontSize = 16.sp, color = Palette1),
            modifier = Modifier.padding(start = 18.dp, bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start)
        ) {
            transportIcons.forEachIndexed { index, iconRes ->
                val isSelected = isTransportTypeSelected == index.toString()
                IconButton(
                    onClick = {
                        isTransportTypeSelected = index.toString()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(0.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isSelected) Palette1 else Color.White)
                ) {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = "Transport Type",
                        tint = if (isSelected) Color.White else Palette1,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {navController.navigate("flight")},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Palette1,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Search",
                    style = TextStyle(fontSize = 24.sp, color = Color.White),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}