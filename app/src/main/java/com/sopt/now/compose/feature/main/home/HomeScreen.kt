package com.sopt.now.compose.feature.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sopt.now.compose.component.profile.FriendProfileItem
import com.sopt.now.compose.component.profile.MyProfileItem
import com.sopt.now.compose.data.model.FriendInfo

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel()
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            items(homeViewModel.friendList) { friend ->
                when (friend) {
                    is FriendInfo.MyProfile -> MyProfileItem(
                        name = friend.name,
                        profileImage = friend.profileImage,
                        profileImageEtc = friend.profileImageEtc
                    )

                    is FriendInfo.FriendProfile -> FriendProfileItem(
                        name = friend.name,
                        profileImage = friend.profileImage,
                        selfDescription = friend.selfDescription
                    )
                }
            }
        }
    }
}
