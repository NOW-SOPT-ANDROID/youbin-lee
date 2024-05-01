package com.sopt.now.presentation.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.di.ServicePool.authService
import com.sopt.now.data.dto.request.LoginRequestDto
import com.sopt.now.presentation.User
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginState = MutableLiveData<Boolean>()
    val loginState: MutableLiveData<Boolean> get() = _loginState

    private lateinit var user: User

    private var memberId: String? = null

    fun setUser(user: User) {
        this.user = user
    }

//    fun setMemberId(memberId: String) {
//        this.memberId = memberId
//    }

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
                    memberId = it.headers()["Location"]?.split("/")?.last()
                    _loginState.value = true
                }
                .onFailure {
                    _loginState.value = false
                }
        }

    }

}