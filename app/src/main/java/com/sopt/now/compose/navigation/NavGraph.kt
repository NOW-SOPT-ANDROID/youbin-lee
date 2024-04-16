package com.sopt.now.compose.navigation

sealed class NavGraph(val route: String) {
    data object Auth : NavGraph(route = "AUTH")
    data object Main : NavGraph(route = "MAIN")
}