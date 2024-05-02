package com.sopt.now.compose.feature.main.search

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.now.compose.component.profile.FriendProfileItem
import com.sopt.now.compose.component.profile.MyProfileItem
import com.sopt.now.compose.feature.main.home.FriendInfo

@Composable
fun SearchRoute(
    searchViewModel: SearchViewModel = viewModel()
) {
    val searchState by searchViewModel.state.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var followerList: List<FollowerInfo>? = null

    LaunchedEffect(true) {
        searchViewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { searchSideEffect ->
                when (searchSideEffect) {
                    is SearchSideEffect.Success -> {
                        followerList = searchSideEffect.followerList
                    }

                    SearchSideEffect.Failure -> Toast.makeText(
                        context,
                        "서버통신 실패",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }

    followerList?.let {
        SearchScreen(
            searchState = searchState,
            followerList = it
        )
    }

}

@Composable
fun SearchScreen(
    searchState: SearchState,
    followerList: List<FollowerInfo>
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
//            items(followerList) { friend ->
//                when (friend) {
//                    is FriendInfo.MyProfile -> MyProfileItem(
//                        name = friend.name,
//                        profileImage = friend.profileImage,
//                        profileImageEtc = friend.profileImageEtc
//                    )
//
//                    is FriendInfo.FriendProfile -> FriendProfileItem(
//                        name = friend.name,
//                        profileImage = friend.profileImage,
//                        selfDescription = friend.selfDescription
//                    )
//                }
            }
        }
    }


