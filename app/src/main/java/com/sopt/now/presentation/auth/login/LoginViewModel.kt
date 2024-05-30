package com.sopt.now.presentation.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.entity.request.LoginRequestModel
import com.sopt.now.domain.repository.AuthRepository
import com.sopt.now.presentation.User
import com.sopt.now.presentation.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

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
            repository.postLogin(
                LoginRequestModel(
                    id,
                    pw
                )
            ).onSuccess { response ->
                memberId = response.memberId
                _loginState.value = AuthState.Success
            }.onFailure {
                _loginState.value = AuthState.Failure(it.message.orEmpty())
            }
        }
    }

}