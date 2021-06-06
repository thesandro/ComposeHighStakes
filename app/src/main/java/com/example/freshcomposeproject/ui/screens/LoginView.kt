package com.example.freshcomposeproject.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.freshcomposeproject.R
import com.example.freshcomposeproject.ui.navigation.Screen
import com.google.accompanist.coil.rememberCoilPainter


@Composable
fun LoginView(navController: NavHostController) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    fun navigateToPosts(){
        navController.navigate(Screen.Home.route)
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp),
            painter = rememberCoilPainter(
                "https://www.kickassfacts.com/wp-content/uploads/2021/04/bear.jpg",
                previewPlaceholder = R.drawable.ic_launcher_foreground
            ),
            contentDescription = ""
        )
        TextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("Email") },
            modifier = Modifier.padding(16.dp, 18.dp, 16.dp, 18.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .padding(16.dp, 18.dp, 16.dp, 18.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

        )
        Button(
            onClick = {
                navigateToPosts()
                Toast.makeText(context, "Entered", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .width(200.dp)
                .height(48.dp)
                .padding(0.dp, 8.dp, 0.dp, 0.dp)
        ) {
            Text("Enter")
        }
    }
}