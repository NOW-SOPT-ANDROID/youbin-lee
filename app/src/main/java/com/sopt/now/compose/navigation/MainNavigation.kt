package com.sopt.now.compose.navigation

import BottomNavItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.now.compose.feature.auth.login.LoginScreen
import com.sopt.now.compose.feature.auth.signup.SignUpScreen
import com.sopt.now.compose.feature.main.home.HomeScreen
import com.sopt.now.compose.feature.main.mypage.MyPageScreen
import com.sopt.now.compose.feature.main.search.SearchScreen

@Composable
fun MainNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavGraph.Auth.route
    ) {
        authGraph(navController)
        mainGraph(navController)
    }
}

fun NavGraphBuilder.authGraph(
    navController: NavController
) {
    navigation(startDestination = AuthGraph.Login.route, route = NavGraph.Auth.route) {
        composable(
            route = AuthGraph.SignUp.route
        ) {
            SignUpScreen(navController)
        }
        composable(
            route = AuthGraph.Login.route
        ) {
            LoginScreen(navController)
        }
    }
}

fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(startDestination = BottomNavItem.Home.screenRoute, route = NavGraph.Main.route) {
        composable(
            BottomNavItem.Home.screenRoute
        ) {
            HomeScreen(navController)
        }
        composable(
            BottomNavItem.Search.screenRoute
        ) {
            SearchScreen(navController)
        }
        composable(
            BottomNavItem.MyPage.screenRoute
        ) {
            MyPageScreen(navController)
        }
    }
}