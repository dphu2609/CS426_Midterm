package com.example.midtermproject.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.midtermproject.R
import com.example.midtermproject.components.UserViewModel
import com.example.midtermproject.ui.theme.Palette1


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AccountScreen(navController: NavController, userViewModel: UserViewModel = viewModel()) {
    val accountName = "Victoria Yoker"
    val imageUri by userViewModel.imageUri.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Account",
                color = Color.Black,
                fontSize = 32.sp
            )

            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(model = it),
                    contentDescription = "Account",
                    modifier = Modifier
                        .width(180.dp)
                        .height(180.dp)
                        .padding(20.dp)
                )
            } ?: run {
                Image(
                    painter = painterResource(id = R.drawable.account_image),
                    contentDescription = "Account",
                    modifier = Modifier
                        .width(180.dp)
                        .height(180.dp)
                        .padding(20.dp)
                )
            }

            Text(
                text = accountName,
                color = Color.Black,
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(34.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable(onClick = { navController.navigate("personalinfo") })
        ) {
            Icon(
                painter = painterResource(id = R.drawable.adult_icon),
                contentDescription = "Swap",
                tint = Palette1,
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Personal information",
                color = Color.Black,
                fontSize = 20.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.credit_card_icon),
                contentDescription = "Swap",
                tint = Palette1,
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Payment and cards",
                color = Color.Black,
                fontSize = 20.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.heart_icon),
                contentDescription = "Swap",
                tint = Palette1,
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Saved",
                color = Color.Black,
                fontSize = 20.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.history_icon),
                contentDescription = "Swap",
                tint = Palette1,
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Booking history",
                color = Color.Black,
                fontSize = 20.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.setting_icon),
                contentDescription = "Swap",
                tint = Palette1,
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Settings",
                color = Color.Black,
                fontSize = 20.sp
            )
        }
    }
}
