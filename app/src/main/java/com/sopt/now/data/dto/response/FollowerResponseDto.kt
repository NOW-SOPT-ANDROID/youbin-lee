package com.sopt.now.data.dto.response

import com.sopt.now.domain.entity.response.FollowerResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowerResponseDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("data")
    val data: List<FollowerData>,
    @SerialName("support")
    val support: Support,
) {
    @Serializable
    data class FollowerData(
        @SerialName("id")
        val id: Int,
        @SerialName("email")
        val email: String,
        @SerialName("first_name")
        val first_name: String,
        @SerialName("last_name")
        val last_name: String,
        @SerialName("avatar")
        val avatar: String
    )

    @Serializable
    data class Support(
        @SerialName("url")
        val url: String,
        @SerialName("text")
        val text: String,
    )

    fun toFollowerEntity(): List<FollowerResponseModel> = data.map {
        FollowerResponseModel(
            avatar = it.avatar,
            email = it.email,
            first_name = it.first_name,
            last_name = it.last_name
        )
    }
}