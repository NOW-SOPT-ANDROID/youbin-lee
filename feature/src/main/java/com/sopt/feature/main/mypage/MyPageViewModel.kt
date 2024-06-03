package com.sopt.feature.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.domain.entity.request.UserRequestModel
import com.sopt.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val _state: MutableStateFlow<MyPageState> = MutableStateFlow(MyPageState())
    val state: StateFlow<MyPageState>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val sideEffect: SharedFlow<Boolean> get() = _sideEffect.asSharedFlow()

    private var memberId: Int? = null
    fun fetchMemberId(memberId: Int) {
        this.memberId = memberId
    }

    private fun fetchUserInfo(user: MyPageState) {
        _state.value = _state.value.copy(
            nickname = user.nickname,
            phone = user.phone
        )
    }

    fun getUserInfo() {
        viewModelScope.launch {
            repository.getUser(
                UserRequestModel(
                    userId = memberId ?: return@launch
                )
            ).onSuccess { response ->
                fetchUserInfo(
                    MyPageState(
                        response.phone,
                        response.nickname
                    )
                )
                _sideEffect.emit(true)
            }.onFailure {
                _sideEffect.emit(false)
            }
        }
    }
}