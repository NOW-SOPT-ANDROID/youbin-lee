package com.sopt.now.domain.repository

import com.sopt.now.domain.entity.request.SignUpRequestModel
import com.sopt.now.domain.entity.response.SignUpResponseModel

interface AuthRepository {
    suspend fun postLogin()

    suspend fun postSignUp(request: SignUpRequestModel): Result<SignUpResponseModel>
}