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
            music = "EASY"
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile2,
            name = "배지현",
        ),
        FriendInfo.FriendMusic(
            profileImage = R.drawable.img_friend_profile3,
            name = "최준서",
            music = "Siren"
        ),
        FriendInfo.FriendMusic(
            profileImage = R.drawable.img_friend_profile4,
            name = "김언지",
            music = "SHEESH"
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile5,
            name = "박동민",
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile6,
            name = "배찬우",
        ),
        FriendInfo.FriendMusic(
            profileImage = R.drawable.img_friend_profile7,
            name = "강문수",
            music = "Magnetic"
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile8,
            name = "이현진",
        ),
        FriendInfo.FriendProfile(
            profileImage = R.drawable.img_friend_profile9,
            name = "이나경",
        )

    )
}