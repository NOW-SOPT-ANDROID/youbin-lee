package com.sopt.now.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.now.compose.presentation.auth.login.LoginScreen
import com.sopt.now.compose.presentation.auth.signup.SignUpScreen
import com.sopt.now.compose.presentation.mypage.MainPageScreen

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = ScreenRoute.Login.route) {
        composable(
            route = ScreenRoute.SignUp.route
        ) {
            SignUpScreen(navController)
        }
        composable(
            route = ScreenRoute.Login.route
        ) {
            LoginScreen(navController)
        }
        composable(
            route = ScreenRoute.MainPage.route
        ) {
            MainPageScreen(navController)
        }
    }
}
