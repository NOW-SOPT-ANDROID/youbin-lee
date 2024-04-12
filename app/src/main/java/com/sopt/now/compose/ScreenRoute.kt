package com.sopt.now.compose

sealed class ScreenRoute(val route: String) {
    data object Login : ScreenRoute(route = "login")
    data object SignUp : ScreenRoute(route = "signUp")
    data object MainPage : ScreenRoute(route = "mainPage")
}