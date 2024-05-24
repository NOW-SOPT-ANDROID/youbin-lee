package com.sopt.now.compose.feature.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.R
import com.sopt.now.compose.data.di.ServicePool.followerService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _state: MutableStateFlow<SearchState> =
        MutableStateFlow(SearchState.Empty)
    val state: StateFlow<SearchState> get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SearchSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SearchSideEffect> get() = _sideEffect

    fun getFriendsInfo(page: Int) {
        viewModelScope.launch {
            runCatching {
                _state.value = SearchState.Loading
                followerService.getFollowerListFromServer(page)
            }.onSuccess {
                _state.value = SearchState.Success(it.data)
                _sideEffect.emit(SearchSideEffect.Toast(R.string.server_success))
            }.onFailure {
                _sideEffect.emit(SearchSideEffect.Toast(R.string.server_failure))
            }
        }
    }

    companion object{
        const val PAGE = 2
    }

}
