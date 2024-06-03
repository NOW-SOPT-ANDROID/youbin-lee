package com.sopt.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.sopt.feature.auth.login.navigation.loginGraph
import com.sopt.feature.auth.signup.navigation.signUpGraph
import com.sopt.feature.main.home.navigation.homeGraph
import com.sopt.feature.main.mypage.navigation.myPageGraph
import com.sopt.feature.main.search.navigation.searchGraph
import com.sopt.ui.ui.theme.NOWSOPTAndroidTheme

@Composable
fun MainScreen()
{
    val navController: MainNavigator = rememberMainNavigator()
    NOWSOPTAndroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreenHome(navController)
        }
    }
}


@Composable
fun MainScreenHome(
    navController: MainNavigator = rememberMainNavigator()
) {
    Scaffold(
        bottomBar = {
            BottomNavigation(
                isVisible = navController.showBottomBar(),
                items = BottomNavigationItem.entries.toList(),
                currentItem = navController.currentNavItem,
                onItemSelected = navController::navigateMainNavigation
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            NavHost(
                navController = navController.navHostController,
                startDestination = navController.startDestination
            ) {
                loginGraph(
                    popBackStack = { navController.popBackStack() },
                    onSingUpClick = { navController.signUpNavigation() },
                    onMainClick = {
                        navController.navHostController.navigate(BottomNavigationItem.Home.route)
                    },
                    navHostController = navController.navHostController
                )
                signUpGraph(
                    onLoginClick = { navController.loginNavigation() },
                    navHostController = navController.navHostController
                )
                homeGraph()
                searchGraph()
                myPageGraph(
                    navHostController = navController.navHostController
                )
            }
        }
    }
}