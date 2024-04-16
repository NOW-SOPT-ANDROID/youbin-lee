package com.sopt.now.compose.navigation

sealed class ScreenRoute(val route: String) {
    data object Login : ScreenRoute(route = "login")
    data object SignUp : ScreenRoute(route = "signUp")
    data object MainPage : ScreenRoute(route = "mainPage")
}