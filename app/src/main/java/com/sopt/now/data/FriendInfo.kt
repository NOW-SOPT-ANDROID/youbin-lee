package com.sopt.now.data

import androidx.annotation.DrawableRes

sealed class FriendInfo {

    data class MyProfile(
        val name: String,
        val profileImage: Int,
    ) : FriendInfo()

    data class FriendProfile(
        val name: String,
        @DrawableRes val profileImage: Int,
        val music: String? = "",
    ) : FriendInfo()

}