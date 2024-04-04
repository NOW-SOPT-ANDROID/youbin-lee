package com.sopt.now.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.presentation.auth.login.LoginScreen
import com.sopt.now.compose.presentation.auth.signup.SignUpScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "loginScreen") {
        composable("loginScreen") { LoginScreen(navController = navController) }
        composable("signUp") { SignUpScreen(navController = navController) }
    }
}
