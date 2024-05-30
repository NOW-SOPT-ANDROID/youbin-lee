package com.sopt.now.presentation.auth.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.entity.request.SignUpRequestModel
import com.sopt.now.domain.repository.AuthRepository
import com.sopt.now.presentation.User
import com.sopt.now.presentation.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

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
            repository.postSignUp(
                SignUpRequestModel(
                    user.id,
                    user.pw,
                    user.nickname,
                    user.phone
                )
            )

                .onSuccess {
                    when (it.code) {
                        in SERVER_MIN_CODE..SERVER_MAX_CODE -> {
                            memberId = it.memberId
                            _signUpState.value = AuthState.Success
                        }

                        else -> {
                            _signUpState.value = AuthState.InputError
                        }
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
