package com.sopt.now.compose.navigation

sealed class AuthGraph(val route: String) {
    data object Login : AuthGraph(route = "LOGIN")
    data object SignUp : AuthGraph(route = "SIGN_UP")
}