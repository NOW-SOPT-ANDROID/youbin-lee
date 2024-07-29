package com.sopt.now.presentation.main.home

import androidx.lifecycle.ViewModel
import com.sopt.now.R
import dagger.hilt.android.lifecycle.HiltViewModel

class HomeViewModel : ViewModel() {

    val mockFriendInfoLists = listOf<FriendInfo>(
        FriendInfo.MyProfile(
            profileImage = R.drawable.img_main_profile,
            name = "이유빈",
        ),
        FriendInfo.FriendProfile(
            profileImage = "https://avatars.githubusercontent.com/u/113014331?v=4",
            name = "우상욱",
            music = "EASY"
        ),
        FriendInfo.FriendProfile(
            profileImage = "https://avatars.githubusercontent.com/u/103172971?v=4",
            name = "배지현",
        ),
        FriendInfo.FriendProfile(
            profileImage = "https://avatars.githubusercontent.com/u/127238018?v=4",
            name = "최준서",
            music = "Siren"
        ),
        FriendInfo.FriendProfile(
            profileImage = "https://avatars.githubusercontent.com/u/85453429?v=4",
            name = "김언지",
            music = "SHEESH"
        ),
        FriendInfo.FriendProfile(
            profileImage = "https://avatars.githubusercontent.com/u/52882799?v=4",
            name = "박동민",
        ),
        FriendInfo.FriendProfile(
            profileImage = "https://avatars.githubusercontent.com/u/106955456?v=4",
            name = "배찬우",
            music = "hype boy"
        ),
        FriendInfo.FriendProfile(
            profileImage = "https://avatars.githubusercontent.com/u/85223787?v=4",
            name = "강문수",
            music = "Magnetic"
        ),
        FriendInfo.FriendProfile(
            profileImage = "https://avatars.githubusercontent.com/u/95455569?v=4",
            name = "이현진",
        ),
        FriendInfo.FriendProfile(
            profileImage = "https://avatars.githubusercontent.com/u/109855280?v=4",
            name = "이나경",
        ),
        FriendInfo.FriendProfile(
            profileImage = "https://avatars.githubusercontent.com/u/135544903?v=4",
            name = "조세연",
            music = "잘자요 아가씨"
        )

    )
}