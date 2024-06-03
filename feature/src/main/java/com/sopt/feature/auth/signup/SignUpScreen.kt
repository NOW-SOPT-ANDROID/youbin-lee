package com.sopt.feature.auth.signup

import android.widget.Toast
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.sopt.feature.R
import com.sopt.feature.component.textfield.TextFieldWithTitle
import com.sopt.ui.extension.toast

@Composable
fun SignUpRoute(
    navController: NavHostController,
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    onLoginClick: () -> Unit
) {

    val signUpState by signUpViewModel.state.collectAsStateWithLifecycle(lifecycleOwner = LocalLifecycleOwner.current)
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(signUpViewModel.sideEffect, lifecycleOwner) {
        signUpViewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignUpSideEffect.Success -> {
                        Toast.makeText(
                            context,
                            "회원가입 성공! ID는 " + sideEffect.memberId,
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "memberId",
                            value = sideEffect.memberId
                        )

                        onLoginClick()
                    }

                    is SignUpSideEffect.Failure -> context.toast(sideEffect.message)

                }
            }
    }

    SignUpScreen(
        signUpViewModel = signUpViewModel,
        onSignUpClick = {
            signUpViewModel.checkSignUpAvailable()
        },
        signUpState = signUpState,
    )

}

@Composable
fun SignUpScreen(
    signUpState: SignUpState,
    onSignUpClick: () -> Unit,
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
        TextFieldWithTitle(
            text = stringResource(id = R.string.id),
            value = signUpState.id,
            placeholder = stringResource(id = R.string.sign_up_id_hint),
            onValueChanged = { id ->
                signUpViewModel.fetchId(id)
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        TextFieldWithTitle(
            text = stringResource(id = R.string.pw),
            value = signUpState.password,
            placeholder = stringResource(id = R.string.sign_up_pw_hint),
            onValueChanged = { password ->
                signUpViewModel.fetchPassword(password)
            })
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        TextFieldWithTitle(
            text = stringResource(id = R.string.nickname),
            value = signUpState.nickname,
            placeholder = stringResource(id = R.string.sign_up_nickname_hint),
            onValueChanged = { nickname ->
                signUpViewModel.fetchNickname(nickname)
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        TextFieldWithTitle(
            text = stringResource(id = R.string.phone),
            value = signUpState.phone,
            placeholder = stringResource(id = R.string.sign_up_phone_hint),
            onValueChanged = { phone ->
                signUpViewModel.fetchPhone(phone)
            }
        )
        Spacer(modifier = Modifier.weight(2f))
        Button(
            onClick = onSignUpClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(id = R.string.btn_sign_up))
        }
        Spacer(modifier = Modifier.padding(bottom = 30.dp))
    }
}
