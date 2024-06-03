package com.sopt.data.repositoryimpl

import com.sopt.data.datasource.AuthDataSource
import com.sopt.data.dto.request.LoginRequestDto
import com.sopt.data.dto.request.SignUpRequestDto
import com.sopt.domain.entity.request.LoginRequestModel
import com.sopt.domain.entity.request.SignUpRequestModel
import com.sopt.domain.entity.request.UserRequestModel
import com.sopt.domain.entity.response.LoginResponseModel
import com.sopt.domain.entity.response.SignUpResponseModel
import com.sopt.domain.entity.response.UserResponseModel
import com.sopt.domain.repository.AuthRepository
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
                throw Exception(
                    JSONObject(
                        response.errorBody()?.string().orEmpty()
                    ).getString(MESSAGE)
                )
            }
        }

    override suspend fun getUser(request: UserRequestModel): Result<UserResponseModel> =
        runCatching {
            authDataSource.getUser(request.userId).toUserEntity()
        }

    companion object {
        private const val LOCATION = "Location"
        private const val MESSAGE = "message"
    }
}