package com.example.midtermproject.pages

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.midtermproject.R
import com.example.midtermproject.components.FragmentHeader
import com.example.midtermproject.components.UserViewModel

@Composable
fun PersonalInfoScreen(navController: NavController, userViewModel: UserViewModel = viewModel()) {
    var firstName by remember { mutableStateOf("Victoria") }
    var lastName by remember { mutableStateOf("Yoker") }
    var phoneNumber by remember { mutableStateOf("+380 123456789") }
    var email by remember { mutableStateOf("victoria.yoker@gmail.com") }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            userViewModel.setImageUri(it)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        FragmentHeader(
            title = "Personal information",
            onReturnClick = { navController.popBackStack() }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageUri by userViewModel.imageUri.collectAsState()

            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(model = it),
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .size(180.dp)
                        .clickable { launcher.launch("image/*") }
                        .padding(20.dp)
                )
            } ?: run {
                Image(
                    painter = painterResource(id = R.drawable.account_image),
                    contentDescription = "Account",
                    modifier = Modifier
                        .size(180.dp)
                        .clickable { launcher.launch("image/*") }
                        .padding(20.dp)
                )
            }
        }

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}
