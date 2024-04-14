package com.sopt.now.data

sealed class FriendInfo {

    data class MyProfile(
        val name: String,
        val profileImage: Int,
    ) : FriendInfo()

    data class FriendProfile(
        val name: String,
        val profileImage: Int,
    ) : FriendInfo()

    data class FriendMusic(
        val name: String,
        val profileImage: Int,
        val music: String,
    ) : FriendInfo()

}