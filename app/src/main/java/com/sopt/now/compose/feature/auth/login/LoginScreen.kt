package com.sopt.now.compose.feature.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sopt.now.compose.R
import com.sopt.now.compose.component.textfield.TextFieldWithTitle
import com.sopt.now.compose.util.shortToast
import com.sopt.now.compose.util.toast
import kotlinx.coroutines.launch

@Composable
fun LoginRoute(
    popBackStack: () -> Unit,
    navController: NavHostController,
    onMainClick: () -> Unit,
    onSignUpClick: () -> Unit,
    loginViewModel: LoginViewModel = viewModel()
) {
    val loginState by loginViewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(loginViewModel.sideEffect, lifecycleOwner) {
        loginViewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { loginSideEffect ->
                when (loginSideEffect) {
                    LoginSideEffect.SignUpNavigation -> {
                        popBackStack()
                        onSignUpClick()
                    }

                    is LoginSideEffect.Success -> {
                        context.shortToast(R.string.login_success)

                        popBackStack()
                        onMainClick()

                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "memberId",
                            value = loginSideEffect.memberId
                        )
                    }

                    is LoginSideEffect.ErrorToast -> context.toast(loginSideEffect.message)

                    LoginSideEffect.Failure -> context.shortToast(R.string.server_failure)
                }
            }
    }

    LoginScreen(
        loginState = loginState,
        loginViewModel = loginViewModel,
        onSignUpClick = {
            scope.launch {
                loginViewModel.signUpClick()
            }
        },
        onMainClick = {
            scope.launch {
                loginViewModel.checkLoginAvailable()
            }
        }
    )
}

@Composable
fun LoginScreen(
    loginState: LoginState,
    loginViewModel: LoginViewModel,
    onMainClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
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
        TextFieldWithTitle(text = stringResource(id = R.string.id),
            value = loginState.id,
            placeholder = stringResource(id = R.string.login_id_hint),
            onValueChanged = { id ->
                loginViewModel.fetchId(id)
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        TextFieldWithTitle(
            text = stringResource(id = R.string.pw),
            value = loginState.password,
            placeholder = stringResource(id = R.string.login_pw_hint),
            onValueChanged = { password ->
                loginViewModel.fetchPassword(password)
            }
        )
        Spacer(modifier = Modifier.weight(2f))
        Button(
            onClick = onMainClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(id = R.string.btn_login))
        }
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Button(
            onClick = onSignUpClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.btn_sign_up))
        }
        Spacer(modifier = Modifier.padding(bottom = 30.dp))
    }
}
