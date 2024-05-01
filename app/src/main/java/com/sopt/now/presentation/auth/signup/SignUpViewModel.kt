package com.sopt.now.presentation.auth.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.entity.request.SignUpRequestModel
import com.sopt.now.domain.repository.AuthRepository
import com.sopt.now.presentation.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private lateinit var user: User

    private val _signUpState = MutableLiveData<Boolean>()
    val signUpState: MutableLiveData<Boolean> get() = _signUpState


    fun setUser(user: User) {
        this.user = user
    }

    fun getUser() = user

    fun checkSignUpAvailable() {
        viewModelScope.launch {
            runCatching {
                repository.postSignUp(
                    SignUpRequestModel(
                        user.id,
                        user.pw,
                        user.nickname,
                        user.phone
                    )
                )
            }
                .onSuccess {
                    _signUpState.value = true
                }
                .onFailure {
                    _signUpState.value = false
                }
        }
    }

}

