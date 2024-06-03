package com.sopt.feature.main.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.feature.main.home.HomeRoute

fun NavController.homeNavigation(navOptions: NavOptions? = null) {
    navigate(
        route = Home.ROUTE,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.homeGraph() {
    composable(route = Home.ROUTE) {
        HomeRoute()
    }
}

object Home {
    const val ROUTE = "HOME"
}