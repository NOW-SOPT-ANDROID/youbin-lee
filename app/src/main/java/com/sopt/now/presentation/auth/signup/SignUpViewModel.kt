package com.sopt.now.presentation.auth.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.di.ServicePool.authService
import com.sopt.now.data.dto.request.SignUpRequestDto
import com.sopt.now.presentation.User
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private lateinit var user: User

    private val _signUpState = MutableLiveData<Boolean>()
    val signUpState: MutableLiveData<Boolean> get() = _signUpState

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
                    memberId = it.headers()["Location"]?.split("/")?.last()
                    _signUpState.value = true
                }
                .onFailure {
                    _signUpState.value = false
                }
        }
    }
}

