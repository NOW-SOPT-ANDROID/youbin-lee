package com.sopt.feature.main.home

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
import com.sopt.component.profile.FriendProfileItem
import com.sopt.component.profile.MyProfileItem

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = viewModel()
) {
    HomeScreen(friendList = homeViewModel.friendList)
}

@Composable
fun HomeScreen(
    friendList: List<FriendInfo>
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
            items(friendList) { friend ->
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