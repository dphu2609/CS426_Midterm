package com.example.midtermproject.components.bookingscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.midtermproject.ui.theme.Palette3

@Composable
fun BookOption(title: String, imageRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.size(200.dp)
            )
            if (title == "Hotel") {
                Button(
                    onClick = { /* Handle booking */ },
                    modifier = Modifier.align(Alignment.TopStart).padding(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Palette3)
                ) {
                    Text("BOOK", color = Color.White)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = title)
    }
}