package com.example.midtermproject.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.midtermproject.R
import com.example.midtermproject.ui.theme.Palette3

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    Row(
        modifier = modifier
            .background(Palette3, shape = MaterialTheme.shapes.small)
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(12.dp))
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            modifier = Modifier.weight(1f)
        ) { innerTextField ->
            if (text.isEmpty()) {
                Text(
                    text = "Search...",
                    style = TextStyle(color = Color.Gray, fontSize = 18.sp)
                )
            }
            innerTextField()
        }
        Button(
            onClick = { /* TODO: Add search functionality */ },
            colors = ButtonDefaults.buttonColors(containerColor = Palette3),
        ) {
            Image(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "Search",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

