package com.sopt.now.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.auth.login.LoginScreen
import com.sopt.now.compose.feature.auth.signup.SignUpScreen
import com.sopt.now.compose.feature.main.mypage.MainPageScreen

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
