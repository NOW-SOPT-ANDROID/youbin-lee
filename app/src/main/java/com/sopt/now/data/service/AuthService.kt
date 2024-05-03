package com.sopt.now.data.service

import com.sopt.now.data.dto.request.LoginRequestDto
import com.sopt.now.data.dto.request.SignUpRequestDto
import com.sopt.now.data.dto.response.LoginResponseDto
import com.sopt.now.data.dto.response.SignUpResponseDto
import com.sopt.now.data.dto.response.UserResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun postSignUpToServer(
        @Body request: SignUpRequestDto,
    ): Response<SignUpResponseDto>

    @POST("member/login")
    suspend fun postLoginToServer(
        @Body request: LoginRequestDto,
    ): Response<LoginResponseDto>

    @GET("member/info")
    suspend fun getUserFromServer(
        @Header("memberId") userId: Int,
    ): Response<UserResponseDto>

}