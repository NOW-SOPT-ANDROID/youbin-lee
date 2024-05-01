package com.sopt.now.data.datasourceimpl

import com.sopt.now.data.datasource.AuthDataSource
import com.sopt.now.data.dto.request.SignUpRequestDto
import com.sopt.now.data.dto.response.SignUpResponseDto
import com.sopt.now.data.service.AuthService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val authService: AuthService) :
    AuthDataSource {
    override suspend fun postSignUp(signUpRequestDto: SignUpRequestDto): SignUpResponseDto =
        authService.postSignUp(
            SignUpRequestDto(
                signUpRequestDto.authenticationId,
                signUpRequestDto.nickname,
                signUpRequestDto.nickname,
                signUpRequestDto.phone
            )
        )
}