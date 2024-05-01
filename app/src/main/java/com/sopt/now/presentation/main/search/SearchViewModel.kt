package com.sopt.now.presentation.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.entity.response.FollowerResponseModel
import com.sopt.now.domain.repository.FollowerRepository
import com.sopt.now.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: FollowerRepository) : ViewModel() {

    private val _followerState =
        MutableStateFlow<UiState<List<FollowerResponseModel>>>(UiState.Loading)
    val followerState: StateFlow<UiState<List<FollowerResponseModel>>> get() = _followerState

    fun getFollowerListFromServer(page: Int) {
        viewModelScope.launch {
            repository.getFollowerList(
                page
            )
                .onSuccess { followerEntityList ->
                    val followerDataList = followerEntityList.map { entity ->
                        FollowerResponseModel(
                            avatar = entity.avatar,
                            email = entity.email,
                            first_name = entity.first_name,
                            last_name = entity.last_name
                        )
                    }
                    _followerState.value = UiState.Success(followerDataList)
                }
                .onFailure {
                    _followerState.value = UiState.Failure(it.message.toString())
                }
        }
    }
}