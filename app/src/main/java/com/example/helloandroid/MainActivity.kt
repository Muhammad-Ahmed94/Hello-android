package com.example.helloandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.helloandroid.ui.theme.HelloAndroidTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloAndroidTheme {
                // switch batween screens
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavHost(
                        navController = navController,
                        startDestination = "signin", //start at signin screen
                        //modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("signin") { SignInScreen(navController) }
                        composable("signout") { SignOutScreen(navController) }
                        composable("forgot_password") { ForgotPasswordScreen(navController) }
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
//sign in screeen here
@Composable
fun SignInScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showAlert by remember { mutableStateOf(false) }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            confirmButton = { Button(onClick = { showAlert = false }) { Text("Go Back") } }, //to back to home screen button
            title = { Text("Sign In") }, // modal ki main heading
            text = { Text("You are signed in!") }//text
        )
    }

    // layout to center the content in teh screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField( //user name text input
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }, //paceholder
            modifier = Modifier.fillMaxWidth() // To keep the input inside the screen
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(// password ki text field
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { showAlert = true }) { Text("Sign In") } //sign in button
        Spacer(modifier = Modifier.height(8.dp))
        // routing to go tto other screens
        TextButton(onClick = { navController.navigate("forgot_password") }) { Text("Forgot Password?") }
        TextButton(onClick = { navController.navigate("signout") }) { Text("Go to Sign Out") }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignOutScreen(navController: NavHostController) {
    var showAlert by remember { mutableStateOf(false) }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            confirmButton = { Button(onClick = { showAlert = false }) { Text("Go Back") } },
            title = { Text("Sign Out") }, //sign out modal ki heading
            text = { Text("You are signed out!") }// uska text
        )
    }

    // Centering the content
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showAlert = true }) { Text("Sign Out") }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var showAlert by remember { mutableStateOf(false) }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            confirmButton = { Button(onClick = { showAlert = false }) { Text("Go Back") } },
            title = { Text("Password Reset") },// modal ki main heading
            text = { Text("Check your inbox to reset your password!") }//uska text
        )
    }

    // style to center the content
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(//forgot email enter field
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { showAlert = true }) { Text("Send Email") }
    }
}
