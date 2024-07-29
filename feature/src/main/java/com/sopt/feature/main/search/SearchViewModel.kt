package com.sopt.feature.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.domain.entity.response.FollowerResponseModel
import com.sopt.domain.repository.FollowerRepository
import com.sopt.feature.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: FollowerRepository) :
    ViewModel() {

    private val _state: MutableStateFlow<SearchState> =
        MutableStateFlow(SearchState.Empty)
    val state: StateFlow<SearchState> get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SearchSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SearchSideEffect> get() = _sideEffect

    fun getFriendsInfo(page: Int) {
        viewModelScope.launch {
            _state.value = SearchState.Loading
            repository.getFollowerList(
                page
            ).onSuccess { response ->
                val followerDataList = response.map { entity ->
                    FollowerResponseModel(
                        avatar = entity.avatar,
                        email = entity.email,
                        firstName = entity.firstName,
                        lastName = entity.lastName
                    )
                }
                _state.value = SearchState.Success(followerDataList)
                _sideEffect.emit(SearchSideEffect.Toast(R.string.server_success))
            }.onFailure {
                _sideEffect.emit(SearchSideEffect.Toast(R.string.server_failure))
            }
        }
    }

    companion object {
        const val PAGE = 2
    }

}
