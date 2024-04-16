package com.sopt.now.compose.navigation

import BottomNavItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.now.compose.MainBottomNav
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
        startDestination = MainGraph.Auth.route
    ) {
        authGraph(navController)
        mainGraph(navController)
    }
}

fun NavGraphBuilder.authGraph(
    navController: NavController
) {
    navigation(startDestination = AuthGraph.Login.route, route = MainGraph.Auth.route) {
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
    navigation(startDestination = BottomNavItem.Home.route, route = MainGraph.Main.route) {
        composable(
            BottomNavItem.Home.route
        ) {
            MainBottomNav(navController = navController) {
                HomeScreen(navController)
            }
        }
        composable(
            BottomNavItem.Search.route
        ) {
            MainBottomNav(navController = navController) {
                SearchScreen(navController)
            }
        }
        composable(
            BottomNavItem.MyPage.route
        ) {
            MainBottomNav(navController = navController) {
                MyPageScreen(navController)
            }
        }
    }
}