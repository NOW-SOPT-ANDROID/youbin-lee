package com.sopt.now.compose.data.service

import com.sopt.now.compose.data.dto.response.FollowerResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("api/users")
    suspend fun getFollowerListFromServer(
        @Query("page") page: Int,
    ): FollowerResponseDto
}