package com.sopt.now.compose.feature.main.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.now.compose.component.profile.FollowerItem
import com.sopt.now.compose.data.dto.response.FollowerResponseDto
import com.sopt.now.compose.feature.main.search.SearchViewModel.Companion.PAGE
import com.sopt.now.compose.util.shortToast

@Composable
fun SearchRoute(
    searchViewModel: SearchViewModel = viewModel()
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val state by searchViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
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
    followerList: List<FollowerResponseDto.FollowerData>
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            items(followerList) { friend ->
                FollowerItem(
                    name = friend.first_name,
                    profileImage = friend.avatar,
                    email = friend.email
                )
            }
        }
    }
}
