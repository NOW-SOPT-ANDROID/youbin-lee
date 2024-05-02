package com.sopt.now.compose.feature.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.di.ServicePool.followerService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _sideEffect: MutableSharedFlow<SearchSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SearchSideEffect> get() = _sideEffect

    suspend fun getFriendsInfo(page: Int) {
        viewModelScope.launch {
            runCatching {
                followerService.getFollowerListFromServer(page)
            }.onSuccess {
                _sideEffect.emit(SearchSideEffect.Success(it.data))
            }.onFailure {
                _sideEffect.emit(SearchSideEffect.Failure)
            }
        }
    }

}
