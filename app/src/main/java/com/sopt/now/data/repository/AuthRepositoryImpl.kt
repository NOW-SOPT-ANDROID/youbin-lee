package com.sopt.now.data.repository

import com.sopt.now.data.datasource.AuthDataSource
import com.sopt.now.data.dto.request.LoginRequestDto
import com.sopt.now.data.dto.request.SignUpRequestDto
import com.sopt.now.domain.entity.request.LoginRequestModel
import com.sopt.now.domain.entity.request.SignUpRequestModel
import com.sopt.now.domain.entity.response.LoginResponseModel
import com.sopt.now.domain.entity.response.SignUpResponseModel
import com.sopt.now.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authDataSource: AuthDataSource) :
    AuthRepository {
    override suspend fun postLogin(request: LoginRequestModel): Result<LoginResponseModel> =
        runCatching {
            val response = authDataSource.postLogin(
                LoginRequestDto(
                    request.authenticationId,
                    request.password
                )
            )
            LoginResponseModel(
                code = response.code(),
                memberId = response.headers()["location"]
            )
        }

    override suspend fun postSignUp(request: SignUpRequestModel): Result<SignUpResponseModel> =
        runCatching {
            val response = authDataSource.postSignUp(
                SignUpRequestDto(
                    request.authenticationId,
                    request.password,
                    request.nickname,
                    request.phone
                )
            )
            SignUpResponseModel(
                code = response.code(),
                memberId = response.headers()["location"]
            )
        }

}