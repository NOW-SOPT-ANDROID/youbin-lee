package com.sopt.now.compose.feature.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.di.ServicePool.followerService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _state: MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SearchSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SearchSideEffect> get() = _sideEffect

    fun getFollowerList(page: Int) {
        viewModelScope.launch {
            runCatching {
                followerService.getFollowerListFromServer(
                    page
                )
            }
                .onSuccess { followerEntityList ->
                    val followerDataList = followerEntityList.toFollowerEntity()
                    _sideEffect.emit(SearchSideEffect.Success(followerDataList))
                }
                .onFailure {
                    _sideEffect.emit(SearchSideEffect.Failure)
                }
        }
    }
}