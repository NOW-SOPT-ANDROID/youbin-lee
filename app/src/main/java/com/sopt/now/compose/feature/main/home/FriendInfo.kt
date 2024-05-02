package com.sopt.now.compose.feature.main.home

sealed class FriendInfo {

    data class MyProfile(
        val name: String,
        val profileImage: Int,
        val profileImageEtc: Int,
    ) : FriendInfo()

    data class FriendProfile(
        val name: String,
        val profileImage: String,
        val selfDescription: String,
    ) : FriendInfo()

}