package com.sopt.now.presentation.auth.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.di.ServicePool.authService
import com.sopt.now.data.dto.request.SignUpRequestDto
import com.sopt.now.presentation.User
import com.sopt.now.presentation.auth.AuthState
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private lateinit var user: User

    private val _signUpState = MutableLiveData<AuthState>()
    val signUpState: MutableLiveData<AuthState> get() = _signUpState

    private var memberId: String? = null

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser() = user

    fun getMemberId() = memberId

    fun checkSignUpAvailable() {
        viewModelScope.launch {
            runCatching {
                authService.postSignUpToServer(
                    SignUpRequestDto(
                        user.id,
                        user.pw,
                        user.nickname,
                        user.phone
                    )
                )
            }
                .onSuccess {
                    when (it.body()?.code) {
                        in SERVER_MIN_CODE..SERVER_MAX_CODE -> {
                            memberId = it.headers()["Location"]?.split("/")?.last()
                            _signUpState.value = AuthState.Success
                        }

                        else -> _signUpState.value = AuthState.InputError
                    }
                }
                .onFailure {
                    _signUpState.value = AuthState.Failure
                }
        }
    }

    companion object {
        private const val SERVER_MIN_CODE = 200
        private const val SERVER_MAX_CODE = 209
    }
}
