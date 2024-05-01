package com.sopt.now.presentation.auth.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.entity.request.SignUpRequestModel
import com.sopt.now.domain.entity.response.SignUpResponseModel
import com.sopt.now.domain.repository.AuthRepository
import com.sopt.now.presentation.User
import com.sopt.now.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private lateinit var user: User

    private val _signUpState = MutableLiveData<UiState<SignUpResponseModel>>()
    val signUpState: MutableLiveData<UiState<SignUpResponseModel>> get() = _signUpState

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser() = user

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
                    _signUpState.value = UiState.Success(it)
                }
                .onFailure {
                    _signUpState.value = UiState.Failure(it.message.toString())
                }
        }
    }

}

