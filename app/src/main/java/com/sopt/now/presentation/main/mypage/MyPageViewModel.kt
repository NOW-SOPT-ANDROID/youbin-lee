package com.sopt.now.presentation.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.di.ServicePool.authService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {

    var id: String? = ""
    var nickname: String? = ""
    var phone: String? = ""

    private val _myPageState = MutableSharedFlow<Boolean>()
    val myPageState: MutableSharedFlow<Boolean> get() = _myPageState

    fun getUserInfo(memberId: Int) {
        viewModelScope.launch {
            runCatching {
                authService.getUserFromServer(memberId)
            }
                .onSuccess {
                    id = it.body()?.data?.authenticationId
                    nickname = it.body()?.data?.nickname
                    phone = it.body()?.data?.phone
                    _myPageState.emit(true)
                }

                .onFailure {
                    _myPageState.emit(false)
                }
        }
    }
}