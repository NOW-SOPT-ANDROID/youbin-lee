package com.sopt.now.compose.data.model

sealed class FriendInfo {
    data class MyProfile(
        val name: String,
        val profileImage: Int,
        val profileImageEtc: Int,
    ) : FriendInfo()

    data class FriendProfile(
        val name: String,
        val profileImage: Int,
        val selfDescription: String,
    ) : FriendInfo()

}