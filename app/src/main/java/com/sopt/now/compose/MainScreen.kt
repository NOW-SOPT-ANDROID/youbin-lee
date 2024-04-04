package com.sopt.now.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.now.compose.presentation.auth.login.LoginScreen

@Composable
fun MainScreen(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "loginScreen"
    ) {
        composable("loginScreen") {
            LoginScreen(navController)
        }
    }
}