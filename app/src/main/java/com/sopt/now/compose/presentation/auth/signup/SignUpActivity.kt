package com.sopt.now.compose.presentation.auth.signup

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import com.sopt.now.compose.R
import com.sopt.now.compose.presentation.auth.login.LoginActivity
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme


class SignActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUpScreen()
                }
            }
        }
    }
}

@Composable

fun SignUpScreen() {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }
    var checkId by remember { mutableStateOf(false) }
    var checkPassword by remember { mutableStateOf(false) }
    var checkNickname by remember { mutableStateOf(false) }
    var checkMbti by remember { mutableStateOf(false) }

    val context = LocalContext.current

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
            value = id,
            onValueChange = {
                id = it
                checkId = id.trim().length in 6..10
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(id = R.string.sign_up_id_hint)) }
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(stringResource(id = R.string.pw))
        TextField(
            value = password,
            onValueChange = {
                password = it
                checkPassword = password.trim().length in 8..12
            },

            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(id = R.string.sign_up_pw_hint)) },
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(stringResource(id = R.string.nickname))
        TextField(
            value = nickname,
            onValueChange = {
                nickname = it
                checkNickname = nickname.trim().isNotEmpty()
            },

            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(id = R.string.sign_up_nickname_hint)) }
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(stringResource(id = R.string.mbti))
        TextField(
            value = mbti,
            onValueChange = {
                mbti = it
                checkMbti = mbti.trim().length in 1..3
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(id = R.string.sign_up_mbti_hint)) }
        )
        Spacer(modifier = Modifier.weight(2f))
        Button(
            onClick = {
                when {
                    !checkId -> Toast.makeText(
                        context,
                        context.getString(R.string.sign_up_id_error),
                        Toast.LENGTH_SHORT
                    ).show()

                    !checkPassword -> Toast.makeText(
                        context,
                        context.getString(R.string.sign_up_pw_error),
                        Toast.LENGTH_SHORT
                    ).show()

                    !checkNickname && !checkMbti -> Toast.makeText(
                        context,
                        context.getString(R.string.sign_up_nickname_mbti_error),
                        Toast.LENGTH_SHORT
                    ).show()

                    else -> {
                        val intent = Intent(context, LoginActivity::class.java).apply {
                            putExtras(
                                bundleOf(
                                    "id" to id,
                                    "pw" to password,
                                    "nickname" to nickname,
                                    "mbti" to mbti
                                )
                            )
                        }
                        (context as? Activity)?.setResult(RESULT_OK, intent)
                        Toast.makeText(
                            context,
                            context.getString(R.string.sign_up_success),
                            Toast.LENGTH_SHORT
                        ).show()
                        (context as? Activity)?.finish()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(id = R.string.btn_sign_up))
        }
        Spacer(modifier = Modifier.padding(bottom = 30.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLogin() {
    SignUpScreen()
}
