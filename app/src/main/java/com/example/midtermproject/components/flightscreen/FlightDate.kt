package com.example.midtermproject.components.flightscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.midtermproject.ui.theme.Palette1
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FlightDate(startDate: String, endDate: String, onDateSelected: (LocalDate) -> Unit) {
    val startDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val start = LocalDate.parse(startDate, startDateFormatter)
    val end = LocalDate.parse(endDate, startDateFormatter)

    var selectedStartDate by remember { mutableStateOf(start) }

    val dates = remember { mutableStateListOf<LocalDate>() }
    var currentStart by remember { mutableStateOf(start) }
    val coroutineScope = rememberCoroutineScope()

    var currentMonth by remember { mutableStateOf(start.month) }
    var currentYear by remember { mutableStateOf(start.year) }

    LaunchedEffect(Unit) {
        dates.addAll(generateDates(start.minusDays(1), end))
        onDateSelected(start)
    }

    // Generate more dates when scrolling left
    fun loadMorePreviousDates() {
        val newDates = generateDates(currentStart.minusDays(7), currentStart.minusDays(1))
        dates.addAll(0, newDates)
        currentStart = currentStart.minusDays(7)
    }

    // Generate more dates when scrolling right
    fun loadMoreNextDates() {
        val newDates = generateDates(dates.last().plusDays(1), dates.last().plusDays(7))
        currentStart = dates.last()
        dates.addAll(newDates)
    }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "${currentMonth.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())}  $currentYear",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 6.dp)
                    .padding(horizontal = 8.dp)
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .scrollable(
                        state = rememberScrollableState { delta ->
                            if (delta > 0) {
                                coroutineScope.launch {
                                    loadMorePreviousDates()
                                }
                            }
                            if (delta < 0) {
                                coroutineScope.launch {
                                    loadMoreNextDates()
                                }
                            }
                            delta
                        },
                        orientation = Orientation.Horizontal
                    )
            ) {
                items(dates) { date ->
                    val isStartDate = date == selectedStartDate
                    DateItem(
                        date = date,
                        isStartDate = isStartDate,
                        onClick = {
                            selectedStartDate = date
                            currentMonth = date.month
                            currentYear = date.year
                            onDateSelected(date) // Call the setter function
                        }
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateItem(date: LocalDate, isStartDate: Boolean, onClick: () -> Unit = {}) {
    val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
    val dayOfMonth = date.dayOfMonth.toString()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isStartDate) Palette1 else Color.Transparent)
            .clickable { onClick() }
    ) {
        Text(
            text = dayOfWeek,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
        Text(
            text = dayOfMonth,
            fontSize = 14.sp,
            color = Color.White
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun generateDates(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    var date = startDate
    while (date.isBefore(endDate) || date.isEqual(endDate)) {
        dates.add(date)
        date = date.plusDays(1)
    }
    return dates
}
