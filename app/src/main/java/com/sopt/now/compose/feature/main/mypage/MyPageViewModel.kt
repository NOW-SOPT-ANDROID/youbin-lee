package com.sopt.now.compose.feature.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.di.ServicePool.authService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {

    private val _state: MutableStateFlow<MyPageState> = MutableStateFlow(MyPageState())
    val state: StateFlow<MyPageState>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val sideEffect: SharedFlow<Boolean> get() = _sideEffect.asSharedFlow()

    private var memberId: Int? = null
    fun setMemberId(memberId: Int) {
        this.memberId = memberId
    }

    private fun setUserInfo(user: MyPageState) {
        _state.value = _state.value.copy(
            nickname = user.nickname,
            phone = user.phone
        )
    }

    fun getUserInfo() {
        viewModelScope.launch {
            runCatching {
                memberId?.let { authService.getUserFromServer(it) }
            }.onSuccess { response ->
                setUserInfo(
                    MyPageState(
                        response?.body()?.data?.phone.orEmpty(),
                        response?.body()?.data?.nickname.orEmpty()
                    )
                )
                _sideEffect.emit(true)
            }.onFailure {
                _sideEffect.emit(false)
            }
        }
    }
}