package com.sopt.now.data.repository

import com.sopt.now.data.datasource.AuthDataSource
import com.sopt.now.data.dto.request.LoginRequestDto
import com.sopt.now.data.dto.request.SignUpRequestDto
import com.sopt.now.domain.entity.request.LoginRequestModel
import com.sopt.now.domain.entity.request.SignUpRequestModel
import com.sopt.now.domain.entity.response.LoginResponseModel
import com.sopt.now.domain.entity.response.SignUpResponseModel
import com.sopt.now.domain.repository.AuthRepository
import org.json.JSONObject
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
            if (response.isSuccessful) {
                response.headers()[LOCATION].run {
                    LoginResponseModel(
                        code = response.code(),
                        memberId = this ?: throw Exception(response.message())
                    )
                }
            } else {
                throw Exception(
                    JSONObject(
                        response.errorBody()?.string().orEmpty()
                    ).getString(MESSAGE)
                )
            }
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
            if (response.isSuccessful) {
                response.headers()[LOCATION].run {
                    SignUpResponseModel(
                        code = response.code(),
                        memberId = this ?: throw Exception(response.message())
                    )
                }
            } else {
                throw Exception(JSONObject(response.errorBody()?.string()).getString(MESSAGE))
            }
        }

    companion object {
        private const val LOCATION = "Location"
        private const val MESSAGE = "message"
    }
}
