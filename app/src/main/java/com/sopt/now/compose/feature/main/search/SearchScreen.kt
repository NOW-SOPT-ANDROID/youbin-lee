package com.sopt.now.compose.feature.main.search

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.now.compose.component.profile.FollowerItem
import com.sopt.now.compose.data.dto.response.FollowerResponseDto


@Composable
fun SearchRoute(
    searchViewModel: SearchViewModel = viewModel()
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var followerList by remember { mutableStateOf<List<FollowerResponseDto.FollowerData>?>(null) }

    LaunchedEffect(true) {
        searchViewModel.getFriendsInfo(2)
    }

    LaunchedEffect(searchViewModel.sideEffect, lifecycleOwner) {
        searchViewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { searchSideEffect ->
                when (searchSideEffect) {
                    is SearchSideEffect.Success -> {
                        followerList = searchSideEffect.followerList
                        Toast.makeText(
                            context,
                            "서버통신 성공",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    SearchSideEffect.Failure -> Toast.makeText(
                        context,
                        "서버통신 실패",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }

    followerList?.let { SearchScreen(followerList = it) }

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
