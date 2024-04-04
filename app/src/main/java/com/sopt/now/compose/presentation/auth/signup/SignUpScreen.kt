package com.sopt.now.compose.presentation.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SignUpScreen(navController: NavHostController) {
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

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
            value = userId,
            onValueChange = { userId = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("아이디를 입력해주세요") }
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text("비밀번호")
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("비밀번호를 입력해주세요") }
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text("닉네임")
        TextField(
            value = nickname,
            onValueChange = { nickname = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("닉네임을 입력해주세요") }
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text("MBTI")
        TextField(
            value = mbti,
            onValueChange = { mbti = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("MBTI를 입력해주세요") }
        )
        Spacer(modifier = Modifier.weight(2f))
        Button(
            onClick = { /* 클릭 시 수행될 동작 */ },
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