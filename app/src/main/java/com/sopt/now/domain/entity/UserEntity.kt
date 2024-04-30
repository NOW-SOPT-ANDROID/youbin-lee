package com.sopt.now.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserEntity(
    val id: String,
    val pw: String,
    val nickname: String,
    val mbti: String
) : Parcelable
