package com.sopt.feature.main.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.component.profile.FollowerItem
import com.sopt.domain.entity.response.FollowerResponseModel
import com.sopt.feature.main.search.SearchViewModel.Companion.PAGE
import com.sopt.ui.extension.shortToast

@Composable
fun SearchRoute(
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val state by searchViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        searchViewModel.getFriendsInfo(PAGE)
    }

    LaunchedEffect(searchViewModel.sideEffect, lifecycleOwner) {
        searchViewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SearchSideEffect.Toast -> context.shortToast(sideEffect.message)
                }
            }
    }

    when (state) {
        is SearchState.Empty -> {}
        is SearchState.Loading -> {}
        is SearchState.Success -> {
            SearchScreen(followerList = (state as SearchState.Success).followerList)
        }
    }

}

@Composable
fun SearchScreen(
    followerList: List<FollowerResponseModel>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        items(followerList) { friend ->
            FollowerItem(
                name = friend.firstName,
                profileImage = friend.avatar,
                email = friend.email
            )
        }
    }
}
