package com.sopt.now.compose.feature.auth.signup

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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sopt.now.compose.R
import com.sopt.now.compose.util.shortToast
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute(
    navController: NavHostController,
    signUpViewModel: SignUpViewModel = viewModel(),
    onLoginClick: () -> Unit
) {

    val signUpState by signUpViewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(signUpViewModel.sideEffect, lifecycleOwner) {
        signUpViewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { signUpSideEffect ->
                when (signUpSideEffect) {
                    is SignUpSideEffect.Success -> {
                        Toast.makeText(
                            context,
                            "회원가입 성공! ID는 " + signUpSideEffect.memberId,
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "memberId",
                            value = signUpSideEffect.memberId
                        )

                        onLoginClick()
                    }

                    SignUpSideEffect.InputError -> context.shortToast(R.string.input_error)


                    SignUpSideEffect.Failure -> context.shortToast(R.string.server_failure)

                }
            }
    }

    SignUpScreen(
        signUpViewModel = signUpViewModel,
        onLoginClick = {
            scope.launch {
                signUpViewModel.checkSignUpAvailable()
            }
        },
        signUpState = signUpState,
    )

}

@Composable
fun SignUpScreen(
    signUpState: SignUpState,
    onLoginClick: () -> Unit,
    signUpViewModel: SignUpViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
    ) {
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.sign_up_title),
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = stringResource(id = R.string.id))
        TextField(
            value = signUpState.id,
            onValueChange = { id ->
                signUpViewModel.setId(id)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(id = R.string.sign_up_id_hint)) }
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(stringResource(id = R.string.pw))
        TextField(
            value = signUpState.password,
            onValueChange = { password ->
                signUpViewModel.setPassword(password)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(id = R.string.sign_up_pw_hint)) },
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(stringResource(id = R.string.nickname))
        TextField(
            value = signUpState.nickname,
            onValueChange = { nickname ->
                signUpViewModel.setNickname(nickname)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(id = R.string.sign_up_nickname_hint)) }
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(stringResource(id = R.string.phone))
        TextField(
            value = signUpState.phone,
            onValueChange = { phone ->
                signUpViewModel.setPhone(phone)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(id = R.string.sign_up_phone_hint)) }
        )
        Spacer(modifier = Modifier.weight(2f))
        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(id = R.string.btn_sign_up))
        }
        Spacer(modifier = Modifier.padding(bottom = 30.dp))
    }
}
