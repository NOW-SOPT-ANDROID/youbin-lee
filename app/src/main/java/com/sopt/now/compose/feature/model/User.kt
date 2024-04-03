package com.sopt.now.compose.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val pw: String = "",
    val nickname: String = "",
    val mbti: String = ""
) : Parcelable