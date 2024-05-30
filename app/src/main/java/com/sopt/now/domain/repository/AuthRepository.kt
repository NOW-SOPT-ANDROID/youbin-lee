package com.sopt.now.domain.repository

import com.sopt.now.domain.entity.request.LoginRequestModel
import com.sopt.now.domain.entity.request.SignUpRequestModel
import com.sopt.now.domain.entity.response.LoginResponseModel
import com.sopt.now.domain.entity.response.SignUpResponseModel

interface AuthRepository {
    suspend fun postLogin(request: LoginRequestModel): Result<LoginResponseModel>

    suspend fun postSignUp(request: SignUpRequestModel): Result<SignUpResponseModel>
}