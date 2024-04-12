package com.sopt.now.compose.presentation.auth.login

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.R
import com.sopt.now.compose.ScreenRoute
import com.sopt.now.compose.model.User
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
    val loginState by loginViewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    LaunchedEffect(loginState.message) {
        loginState.message?.let { message ->
            if (message == "로그인에 성공했습니다") {
                val user = User(
                    id = loginState.id,
                    pw = loginState.pw,
                    nickname = loginState.nickname,
                    mbti = loginState.mbti
                )

                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "User",
                    value = user
                )
                navController.navigate(ScreenRoute.MainPage.route)
            } else {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                loginViewModel.clearMessage()
            }
        }
    }

    LaunchedEffect(true) {
        navController.previousBackStackEntry?.savedStateHandle?.run {
            val user = get<User>("User") ?: User("", "", "", "")
            loginViewModel.setUserInfo(user)
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
            text = stringResource(id = R.string.login_title),
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = stringResource(id = R.string.id))
        TextField(
            value = loginState.id,
            onValueChange = { id ->
                loginViewModel.setId(id)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(id = R.string.login_id_hint)) }
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(stringResource(id = R.string.pw))
        TextField(
            value = loginState.pw,
            onValueChange = { pw ->
                loginViewModel.setPassword(pw)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(id = R.string.login_pw_hint)) },
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.weight(2f))
        Button(
            onClick = {
                scope.launch {
                    loginViewModel.checkLogin()
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(id = R.string.btn_login))
        }
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Button(
            onClick = { navController.navigate(ScreenRoute.SignUp.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.btn_sign_up))
        }
        Spacer(modifier = Modifier.padding(bottom = 30.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLogin() {
    val navController = rememberNavController()
    LoginScreen(navController)
}