package com.sopt.now.presentation.main.home

import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.data.FriendInfo

class HomeViewModel : ViewModel() {

    val mockFriendInfoLists = listOf<FriendInfo>(
        FriendInfo.MyProfile(
            profileImage = R.drawable.img_main_profile,
            name = "이유빈",
        ),
        FriendInfo.FriendMusic(
            profileImage = R.drawable.img_friend_profile1,
            name = "우상욱",
            music = "우주를 줄게"
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile2,
            name = "배지현",
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile2,
            name = "배지현",
        ),   FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile2,
            name = "배지현",
        ),   FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile2,
            name = "배지현",
        ),   FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile2,
            name = "배지현",
        ),   FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile2,
            name = "배지현",
        ),   FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile2,
            name = "배지현",
        ),
    )
}