package com.sopt.now.compose.feature.main.mypage

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.feature.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyPageViewModel : ViewModel() {

    private val _state: MutableStateFlow<MyPageState> = MutableStateFlow(MyPageState())
    val state: StateFlow<MyPageState>
        get() = _state.asStateFlow()

    fun setUserInfo(user: User) {
        _state.value = _state.value.copy(
            id = user.id,
            pw = user.pw,
            nickname = user.nickname,
            mbti = user.mbti
        )
    }
}