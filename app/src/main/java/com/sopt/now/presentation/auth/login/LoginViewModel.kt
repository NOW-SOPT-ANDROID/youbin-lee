package com.sopt.now.presentation.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.di.ServicePool.authService
import com.sopt.now.data.dto.request.LoginRequestDto
import com.sopt.now.presentation.User
import com.sopt.now.presentation.auth.AuthState
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginState = MutableLiveData<AuthState>()
    val loginState: MutableLiveData<AuthState> get() = _loginState

    private lateinit var user: User

    private var memberId: String? = null

    fun setUser(user: User) {
        this.user = user
    }

    fun getMemberId() = memberId

    fun getUser() = user

    fun checkLoginAvailable(id: String, pw: String) {
        viewModelScope.launch {
            runCatching {
                authService.postLoginToServer(
                    LoginRequestDto(
                        id,
                        pw
                    )
                )
            }
                .onSuccess {
                    when (it.body()?.code) {
                        in SERVER_MIN_CODE..SERVER_MAX_CODE -> {
                            memberId = it.headers()["Location"]?.split("/")?.last()
                            _loginState.value = AuthState.Success
                        }

                        else -> _loginState.value = AuthState.InputError
                    }
                }
                .onFailure {
                    _loginState.value = AuthState.Failure
                }
        }
    }

    companion object {
        private const val SERVER_MIN_CODE = 200
        private const val SERVER_MAX_CODE = 209
    }
}