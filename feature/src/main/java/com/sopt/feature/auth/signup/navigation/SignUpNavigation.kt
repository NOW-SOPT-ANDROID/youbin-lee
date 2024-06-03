package com.sopt.feature.auth.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.feature.auth.signup.SignUpRoute

fun NavController.signUpNavigation(navOptions: NavOptions? = null) {
    navigate(
        route = SignUp.ROUTE,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.signUpGraph(
    onLoginClick: () -> Unit,
    navHostController: NavHostController
) {
    composable(route = SignUp.ROUTE) {
        SignUpRoute(
            onLoginClick = onLoginClick,
            navController = navHostController
        )
    }
}

object SignUp {
    const val ROUTE = "SIGN_UP"
}