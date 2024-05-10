package com.sopt.now.compose.feature.main.mypage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
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
import com.sopt.now.compose.ui.theme.Pink40
import com.sopt.now.compose.ui.theme.Pink80
import com.sopt.now.compose.ui.theme.black
import com.sopt.now.compose.ui.theme.white

@Composable
fun MyPageRoute(
    navHostController: NavHostController,
    myPageViewModel: MyPageViewModel = viewModel()
) {

    val myPageState by myPageViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        navHostController.previousBackStackEntry?.savedStateHandle?.run {
            val memberId = get<String>("memberId")?.toInt()
            myPageViewModel.setMemberId(memberId ?: 0)

            myPageViewModel.getUserInfo()
        }
    }

    MyPageScreen(
        myPageState = myPageState
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyPageScreen(
    myPageState: MyPageState,
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
            text = stringResource(id = R.string.phone),
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

        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = stringResource(id = R.string.nickname),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = myPageState.phone,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 40.dp),
        )

        // 애니메이션 1

        var visible by remember { mutableStateOf(false) }
        val density = LocalDensity.current

        Text(
            text = stringResource(id = R.string.main_page_search),
            fontSize = 18.sp,
            color = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 40.dp)
                .clickable {
                    // 클릭 가능하게 만들기
                    visible = !visible // 가시성 상태를 반전
                },
        )

        // visible 값에 따라 자식 컴포저블의 가시성을 애니메이션과 함께 보여줌
        AnimatedVisibility(
            visible = visible,
            // false -> true
            enter = slideInVertically {
                // 상단에서 슬라이드인
                with(density) { -40.dp.roundToPx() }
            } + expandVertically(
                // 상단에서 확장
                expandFrom = Alignment.Top
            ) + fadeIn(
                // 초기 투명도 설정, 초기 알파값 0.3f
                initialAlpha = 0.3f
            ),
            // true -> false
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Text(
                stringResource(id = R.string.main_page_detail),
            )
        }

        //애니메이션 2

        // 만약 상태를 유지하고 싶다면 rememberSaveable 사용하기
        var expanded by remember { mutableStateOf(false) }

        // 애니메이션이 끝날 때까지 값이 지속적으로 갱신되는 State 객체를 반환함
        // 확장되면 패딩이 50.dp로 증가, 그렇지 않으면 0.dp
        val extraPadding by animateDpAsState(
            if (expanded) 50.dp else 0.dp, label = ""
        )

        Surface(
            color = Pink80,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Row(modifier = Modifier.padding(24.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = extraPadding)
                ) {
                    Text(text = "Hello, ")
                    Text(text = "leeeyubin")
                }
                //OutlinedButton: 클릭 시 expanded 상태를 반전시키기
                OutlinedButton(
                    onClick = { expanded = !expanded }
                ) {
                    Text(
                        if (expanded) "Show less" else "Show more",
                        color = black
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MyPagePreview() {
    MyPageScreen(myPageState = MyPageState("hi, hi"))
}