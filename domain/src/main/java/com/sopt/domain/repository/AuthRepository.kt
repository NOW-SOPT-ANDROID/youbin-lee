package com.sopt.domain.repository

import com.sopt.domain.entity.request.LoginRequestModel
import com.sopt.domain.entity.request.SignUpRequestModel
import com.sopt.domain.entity.response.LoginResponseModel
import com.sopt.domain.entity.response.SignUpResponseModel


interface AuthRepository {
    suspend fun postLogin(request: LoginRequestModel): Result<LoginResponseModel>
    suspend fun postSignUp(request: SignUpRequestModel): Result<SignUpResponseModel>
}