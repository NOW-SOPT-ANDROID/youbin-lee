package com.sopt.now.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "loginScreen") {
        //   composable("loginScreen") { LoginScreen(navController = navController) }
        //   composable("signUpScreen") { SignUpScreen.kt(navController = navController) }
    }
}
