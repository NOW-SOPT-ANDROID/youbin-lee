package com.sopt.now.data.datasourceimpl

import com.sopt.now.data.datasource.AuthDataSource
import com.sopt.now.data.dto.request.SignUpRequestDto
import com.sopt.now.data.dto.response.SignUpResponseDto
import com.sopt.now.data.service.AuthService
import retrofit2.Response
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val authService: AuthService) : AuthDataSource {
    override suspend fun postSignUp(request : SignUpRequestDto): Response<SignUpResponseDto> =
        authService.postSignUpToServer(request)

}