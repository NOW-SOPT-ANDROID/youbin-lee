package com.sopt.data.repositoryimpl

import android.util.Log
import com.sopt.data.datasource.AuthDataSource
import com.sopt.data.dto.request.LoginRequestDto
import com.sopt.data.dto.request.SignUpRequestDto
import com.sopt.domain.entity.request.LoginRequestModel
import com.sopt.domain.entity.request.SignUpRequestModel
import com.sopt.domain.entity.response.LoginResponseModel
import com.sopt.domain.entity.response.SignUpResponseModel
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
            Log.d("LYB", "레포지토리에서 확인중")
            val response = authDataSource.postSignUp(
                SignUpRequestDto(
                    request.authenticationId,
                    request.password,
                    request.nickname,
                    request.phone
                )
            )
            Log.d("LYB", "response"+response.body().toString())
            if (response.isSuccessful) {
                Log.d("LYB", "레포지토리에서 성공")
                Log.d("LYB", "response body: ${response.body()?.toString()}")
                response.headers()[LOCATION].run {
                    SignUpResponseModel(
                        code = response.code(),
                        memberId = this ?: throw Exception(response.message())
                    )
                }
            } else {
                Log.d("LYB", "레포지토리에서 실패")
                throw Exception(
                    JSONObject(
                        response.errorBody()?.string().orEmpty()
                    ).getString(MESSAGE)
                )
            }
        }.onFailure {
            Log.e("LYB", "Exception: ${it.message}", it)
        }


    companion object {
        private const val LOCATION = "Location"
        private const val MESSAGE = "message"
    }
}