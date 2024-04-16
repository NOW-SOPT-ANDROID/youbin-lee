package com.sopt.now.compose.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class FriendInfo(
    val name: String,
    val profileImage: ImageVector,
    val selfDescription: String
)