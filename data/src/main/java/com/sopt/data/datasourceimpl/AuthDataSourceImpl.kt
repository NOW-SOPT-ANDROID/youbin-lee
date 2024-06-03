package com.sopt.data.datasourceimpl

import com.sopt.data.datasource.AuthDataSource
import com.sopt.data.dto.request.LoginRequestDto
import com.sopt.data.dto.request.SignUpRequestDto
import com.sopt.data.dto.response.LoginResponseDto
import com.sopt.data.dto.response.SignUpResponseDto
import com.sopt.data.dto.response.UserResponseDto
import com.sopt.data.service.AuthService
import retrofit2.Response
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val authService: AuthService) :
    AuthDataSource {

    override suspend fun postLogin(request: LoginRequestDto): Response<LoginResponseDto> =
        authService.postLoginToServer(request)

    override suspend fun postSignUp(request: SignUpRequestDto): Response<SignUpResponseDto> =
        authService.postSignUpToServer(request)

    override suspend fun getUser(userId: Int): UserResponseDto =
        authService.getUserFromServer(userId)

}