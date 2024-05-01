package com.sopt.now.data.repository

import com.sopt.now.data.datasource.AuthDataSource
import com.sopt.now.data.dto.request.SignUpRequestDto
import com.sopt.now.data.dto.request.toSignUpRequestDto
import com.sopt.now.data.dto.response.SignUpResponseDto
import com.sopt.now.domain.entity.request.SignUpRequestModel
import com.sopt.now.domain.entity.response.SignUpResponseModel
import com.sopt.now.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authDataSource: AuthDataSource) :
    AuthRepository {
//    override suspend fun postSignUp(user: SignUpRequestModel): Result<SignUpResponseModel> =
//        runCatching {
//            authDataSource.postSignUp(user.toSignUpRequestDto()).body()!!.toSignUpModel()
//        }
}
