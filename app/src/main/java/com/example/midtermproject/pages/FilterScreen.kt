package com.example.midtermproject.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.midtermproject.R
import com.example.midtermproject.components.FragmentHeader
import com.example.midtermproject.components.transportscreen.LocationInputField
import com.example.midtermproject.ui.theme.Palette1
import com.example.midtermproject.ui.theme.Palette4
import java.time.LocalTime


data class FilterButtonData(
    val label: String,
    val startTime: LocalTime,
    val endTime: LocalTime
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FilterScreen(
    onClose: (Boolean) -> Unit,
    onDepartureTime: (String) -> Unit,
    onStartPrice: (Double) -> Unit,
    onEndPrice: (Double) -> Unit,
    onSortedBy: (String) -> Unit
) {
    var departureTimePicker by remember { mutableStateOf("all") }
    var arrivalTimePicker by remember { mutableStateOf("all") }
    val filterButtons = listOf(
        FilterButtonData("All", LocalTime.of(0, 0), LocalTime.of(23, 59)),
        FilterButtonData("Day", LocalTime.of(6, 0), LocalTime.of(17, 59)),
        FilterButtonData("Night", LocalTime.of(18, 0), LocalTime.of(5, 59))
    )

    var departureTime by remember { mutableStateOf("all") }

    var startPrice by remember { mutableStateOf("") }
    var endPrice by remember { mutableStateOf("") }

    val facilities: List<Pair<Pair<String, Int>, MutableState<Boolean>>> = listOf(
        Pair("Drink", R.drawable.drink_icon) to remember { mutableStateOf(false) },
        Pair("Food", R.drawable.food_icon) to remember { mutableStateOf(false) },
        Pair("Wifi", R.drawable.wifi_icon) to remember { mutableStateOf(false) },
        Pair("Heater", R.drawable.heater_icon) to remember { mutableStateOf(false) }
    )
    val sortOptions = listOf("Price", "Departure Time")
    val selectedOption = remember { mutableStateOf<String?>(null) }

    // Function to reset all filter states
    fun resetFilters() {
        departureTimePicker = "all"
        arrivalTimePicker = "all"
        facilities.forEach { it.second.value = false } // Reset all facility states
        selectedOption.value = null // Reset selected sort option
    }

    Column {
        FragmentHeader(
            title = "Filters",
            onReturnClick = { onClose(false) }
        )
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .background(Palette4, shape = MaterialTheme.shapes.medium)
        ) {
            FilterSection(
                title = "Departure Time",
                buttons = filterButtons,
                selectedPicker = departureTimePicker,
                onPickerChange = { label ->
                    departureTimePicker = label.lowercase()
                    onDepartureTime(departureTime)
                }
            )
            FilterSection(
                title = "Arrival Time",
                buttons = filterButtons,
                selectedPicker = arrivalTimePicker,
                onPickerChange = { label ->
                    arrivalTimePicker = label.lowercase()
                }
            )
            PriceFilter(
                startPrice = startPrice,
                endPrice = endPrice,
                onStartPriceChange = { startPrice = it },
                onEndPriceChange = { endPrice = it },
                onStartPrice = onStartPrice,
                onEndPrice = onEndPrice
            )
            FacilitiesFilter(
                facilities = facilities
            )
            SortByOptions(
                sortOptions = sortOptions,
                selectedOption = selectedOption,
                onSortedBy = onSortedBy
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        resetFilters()
                    },
                    modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        text = "Reset",
                        fontSize = 22.sp,
                        color = Palette1
                    )
                }
                Button(
                    onClick = {
                        onClose(false)
                    },
                    modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Palette1
                    )
                ) {
                    Text(
                        text = "Done",
                        fontSize = 22.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun FilterSection(
    title: String,
    buttons: List<FilterButtonData>,
    selectedPicker: String,
    onPickerChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = title,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start)
        ) {
            items(buttons.size) { index ->
                val button = buttons[index]
                FilterButton(
                    button = button,
                    isSelected = selectedPicker == button.label.lowercase(),
                    onClick = {
                        onPickerChange(button.label)
                    }
                )
            }
        }
    }
}

@Composable
fun FilterButton(
    button: FilterButtonData,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Palette1 else Color.White,
            contentColor = if (isSelected) Color.White else Palette1
        )
    ) {
        Text(
            text = button.label,
            style = TextStyle(
                fontSize = 16.sp,
                color = if (isSelected) Color.White else Palette1
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun PriceFilter(
    startPrice: String,
    endPrice: String,
    onStartPriceChange: (String) -> Unit,
    onEndPriceChange: (String) -> Unit,
    onStartPrice: (Double) -> Unit,
    onEndPrice: (Double) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Text(
            text = "Price",
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            LocationInputField(
                text = startPrice,
                onTextChange = {
                    onStartPriceChange(it)
                   onStartPrice(it.toDouble())
                },
                placeholder = "Min",
                modifier = Modifier.widthIn(max = 140.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            LocationInputField(
                text = endPrice,
                onTextChange = {
                    onEndPriceChange(it)
                    onEndPrice(it.toDouble())
               },
                placeholder = "Max",
                modifier = Modifier.widthIn(max = 140.dp)
            )
        }
    }
}

@Composable
fun FacilitiesFilter(
    facilities: List<Pair<Pair<String, Int>, MutableState<Boolean>>>
) {
    Text(
        text = "Facilities",
        color = Color.White,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
    )
    Row(
        modifier = Modifier.padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        facilities.forEach { (facility, state) ->
            FacilityIcon(
                facility = facility,
                state = state
            )
        }
    }
}

@Composable
fun FacilityIcon(
    facility: Pair<String, Int>,
    state: MutableState<Boolean>
) {
    IconButton(
        onClick = {
            state.value = !state.value
        },
        modifier = Modifier
            .padding(0.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (state.value) Palette1 else Color.White)
    ) {
        Icon(
            painter = painterResource(id = facility.second),
            contentDescription = "Transport Type",
            tint = if (state.value) Color.White else Palette1,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun SortByOptions(
    sortOptions: List<String>,
    selectedOption: MutableState<String?>,
    onSortedBy: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Sort by",
            color = Color.White,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        sortOptions.forEach { option ->
            Row (
            ) {
                Checkbox(
                    checked = selectedOption.value == option,
                    onCheckedChange = {
                        selectedOption.value = if (selectedOption.value == option) null else option
                        onSortedBy(option)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Palette1,
                        uncheckedColor = Palette1
                    ),
                )
                Text(
                    text = option,
                    color = Color.White,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}