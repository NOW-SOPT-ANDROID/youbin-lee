package com.sopt.now.presentation.main.mypage

import androidx.lifecycle.ViewModel

class MyPageViewModel : ViewModel() {
//    suspend fun getUserInfo() {
//        viewModelScope.launch {
//            runCatching {
//                memberId?.let { authService.getUserFromServer(it) }
//            }
//                .onSuccess { response ->
//                    setUserInfo(
//                        MyPageState(
//                            response?.body()?.data?.phone ?: "",
//                            response?.body()?.data?.nickname ?: ""
//                        )
//                    )
//                    _sideEffect.emit(true)
//                }
//
//                .onFailure {
//                    _sideEffect.emit(false)
//                }
//        }
//    }
}