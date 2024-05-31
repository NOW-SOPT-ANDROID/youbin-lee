package com.sopt.now.compose.feature.main.mypage

import androidx.compose.animation.AnimatedVisibility
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
import com.sopt.now.compose.ui.theme.Pink80
import com.sopt.now.compose.ui.theme.black

@Composable
fun MyPageRoute(
    navHostController: NavHostController,
    myPageViewModel: MyPageViewModel = viewModel()
) {

    val myPageState by myPageViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        navHostController.previousBackStackEntry?.savedStateHandle?.run {
            val memberId = get<String>("memberId")?.toInt()
            myPageViewModel.fetchMemberId(memberId ?: 0)

            myPageViewModel.getUserInfo()
        }
    }

    MyPageScreen(
        myPageState = myPageState
    )
}

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
                    .aspectRatio(1f),
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

        AnimationOne()

        AnimationTwo()

    }

}

@Composable
fun AnimationOne(modifier: Modifier = Modifier) {
    var visible by remember { mutableStateOf(false) }
    val density = LocalDensity.current

    Text(
        text = stringResource(id = R.string.main_page_search),
        fontSize = 18.sp,
        color = Color.Blue,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 40.dp)
            .clickable {
                visible = !visible
            },
    )

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Text(
            stringResource(id = R.string.main_page_detail),
        )
    }

}

@Composable
fun AnimationTwo(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (expanded) 50.dp
        else 0.dp, label = ""
    )

    Surface(
        color = Pink80,
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        Row(modifier = modifier.padding(24.dp)) {
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = stringResource(id = R.string.main_page_text_hello))
                Text(text = stringResource(id = R.string.main_page_text_name))
            }
            OutlinedButton(
                onClick = { expanded = !expanded }
            ) {
                Text(
                    if (expanded) stringResource(id = R.string.main_page_show_less)
                    else stringResource(id = R.string.main_page_show_more),
                    color = black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPagePreview() {
    MyPageScreen(myPageState = MyPageState("hi, hi"))
}