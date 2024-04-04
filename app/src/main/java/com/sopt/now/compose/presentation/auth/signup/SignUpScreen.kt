package com.sopt.now.compose.presentation.auth.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SignUpViewModel = viewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val state = viewModel.state.collectAsStateWithLifecycle(lifecycleOwner).value
    val context = LocalContext.current

    LaunchedEffect(state) {
        when (state) {
            is SignUpState.Success -> {
                Toast.makeText(context, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show()
                navController.navigate("loginScreen")
            }

            is SignUpState.Failure -> {
                Toast.makeText(context, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
    ) {
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "SIGN UP",
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "ID")
        TextField(
            value = viewModel.userId.value,
            onValueChange = { viewModel.userId.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("아이디를 입력해주세요") }
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text("비밀번호")
        TextField(
            value = viewModel.password.value,
            onValueChange = { viewModel.password.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("비밀번호를 입력해주세요") }
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text("닉네임")
        TextField(
            value = viewModel.nickname.value,
            onValueChange = { viewModel.nickname.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("닉네임을 입력해주세요") }
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text("MBTI")
        TextField(
            value = viewModel.mbti.value,
            onValueChange = { viewModel.mbti.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("MBTI를 입력해주세요") }
        )
        Spacer(modifier = Modifier.weight(2f))
        Button(
            onClick = { viewModel.onSignUpClicked() },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("회원가입 하기")
        }
        Spacer(modifier = Modifier.padding(bottom = 30.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSignUp() {
    val navController = rememberNavController()
    SignUpScreen(navController)
}