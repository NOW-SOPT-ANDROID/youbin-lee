package com.sopt.now.compose.feature.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.data.model.FriendInfo
import com.sopt.now.compose.navigation.BottomNavigation

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel()
) {
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    )
    {
        Column(
            modifier = Modifier
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            LazyColumn {
                items(homeViewModel.friendList) {
                    FriendProfileItem(it)
                }
            }
        }
    }
}

@Composable
fun FriendProfileItem(friend: FriendInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(friend.profileImage),
            contentDescription = "img_profile",
            modifier = Modifier
                .size(60.dp)
                .aspectRatio(1f / 1f),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = friend.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(10.dp))
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = friend.selfDescription,
            fontSize = 14.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}