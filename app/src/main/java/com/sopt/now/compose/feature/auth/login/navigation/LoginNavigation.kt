package com.sopt.now.compose.feature.auth.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.now.compose.feature.auth.login.LoginRoute

fun NavController.loginNavigation(navOptions: NavOptions? = null) {
    navigate(
        route = Login.ROUTE,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.loginGraph(
    onSingUpClick: () -> Unit,
    onMainClick: () -> Unit,
    navHostController: NavHostController
) {
    composable(route = Login.ROUTE) {
        LoginRoute(
            onSignUpClick = onSingUpClick,
            onMainClick = onMainClick,
            navController = navHostController
        )
    }
}

object Login {
    const val ROUTE = "LOGIN"
}