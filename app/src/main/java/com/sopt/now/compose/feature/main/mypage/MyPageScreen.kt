package com.sopt.now.compose.feature.main.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sopt.now.compose.R
import com.sopt.now.compose.feature.User

@Composable
fun MyPageRoute(
    navHostController: NavHostController,
    myPageViewModel: MyPageViewModel = viewModel()
) {

    val myPageState by myPageViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        navHostController.previousBackStackEntry?.savedStateHandle?.run {
            val user = get<User>("User") ?: User("", "", "", "")
            myPageViewModel.setUserInfo(user)
        }
    }

    MyPageScreen(
        myPageState = myPageState
    )
}

@Composable
fun MyPageScreen(
    myPageState: MyPageState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_main_compose),
                contentDescription = "img_main_compose",
                modifier = Modifier
                    .size(60.dp)
                    .aspectRatio(1f / 1f),
            )
            Text(
                stringResource(id = R.string.main_page_sub_title),
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 10.dp),
            )
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = stringResource(id = R.string.id),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = myPageState.id,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 40.dp),
        )

        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = stringResource(id = R.string.nickname),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = myPageState.nickname,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 40.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MyPageScreen(MyPageState("hi", "hi", "hi", "hi", "hi"))
}