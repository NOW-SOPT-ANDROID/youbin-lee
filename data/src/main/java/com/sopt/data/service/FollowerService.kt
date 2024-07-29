package com.sopt.data.service

import com.sopt.data.dto.response.FollowerResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("api/users")
    suspend fun getFollowerListFromServer(
        @Query("page") page: Int,
    ): FollowerResponseDto
}