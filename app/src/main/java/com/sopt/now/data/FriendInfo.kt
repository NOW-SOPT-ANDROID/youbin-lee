package com.sopt.now.data

sealed class FriendInfo {

    data class MyProfile(
        val name: String,
        val profileImage: Int,
    ) : FriendInfo()

    data class FriendProfile(
        val name: String,
        val profileImage: String,
        val music: String? = "",
    ) : FriendInfo()

}