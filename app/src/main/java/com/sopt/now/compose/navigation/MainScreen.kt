package com.sopt.now.compose.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.sopt.now.compose.feature.auth.login.navigation.loginGraph
import com.sopt.now.compose.feature.auth.signup.navigation.signUpGraph
import com.sopt.now.compose.feature.main.home.navigation.homeGraph
import com.sopt.now.compose.feature.main.mypage.navigation.myPageGraph
import com.sopt.now.compose.feature.main.search.navigation.searchGraph

@Composable
fun MainScreen(
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