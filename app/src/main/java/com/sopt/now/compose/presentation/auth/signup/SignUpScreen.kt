//package com.sopt.now.compose.presentation.auth.signup
//
//import android.app.Activity
//import android.app.Activity.RESULT_OK
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.core.os.bundleOf
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.sopt.now.compose.presentation.auth.login.LoginActivity
//import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
//
//class SignUpActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            NOWSOPTAndroidTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    SignUpScreen()
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun SignUpScreen(
//    viewModel: SignUpViewModel = viewModel(),
//) {
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val state = viewModel.state.collectAsStateWithLifecycle(lifecycleOwner).value
//    val context = LocalContext.current
//
//    var id by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var nickname by remember { mutableStateOf("") }
//    var mbti by remember { mutableStateOf("") }
//
////    LaunchedEffect(state) {
////        when (state) {
////            is SignUpState.Success -> {
////                Toast.makeText(context, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show()
////            }
////
////            is SignUpState.Failure -> {
////                Toast.makeText(context, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show()
////            }
////        }
////    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 40.dp)
//    ) {
//        Spacer(modifier = Modifier.padding(20.dp))
//        Text(
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            text = "SIGN UP",
//            fontSize = 25.sp
//        )
//        Spacer(modifier = Modifier.weight(1f))
//        Text(text = "ID")
//        TextField(
//            value = id,
//            onValueChange = { id = it },
//            modifier = Modifier.fillMaxWidth(),
//            placeholder = { Text("아이디를 입력해주세요") }
//        )
//        Spacer(modifier = Modifier.padding(vertical = 20.dp))
//        Text("비밀번호")
//        TextField(
//            value = password,
//            onValueChange = { password = it },
//            modifier = Modifier.fillMaxWidth(),
//            placeholder = { Text("비밀번호를 입력해주세요") }
//        )
//        Spacer(modifier = Modifier.padding(vertical = 20.dp))
//        Text("닉네임")
//        TextField(
//            value = nickname,
//            onValueChange = { nickname = it },
//            modifier = Modifier.fillMaxWidth(),
//            placeholder = { Text("닉네임을 입력해주세요") }
//        )
//        Spacer(modifier = Modifier.padding(vertical = 20.dp))
//        Text("MBTI")
//        TextField(
//            value = mbti,
//            onValueChange = { mbti = it },
//            modifier = Modifier.fillMaxWidth(),
//            placeholder = { Text("MBTI를 입력해주세요") }
//        )
//        Spacer(modifier = Modifier.weight(2f))
//        Button(
//            onClick = {
//                //  viewModel.onSignUpClicked()
//                Toast.makeText(context, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show()
//                val intent = Intent(context, LoginActivity::class.java).apply {
//                    putExtras(
//                        bundleOf(
//                            "id" to id,
//                            "password" to password,
//                            "nickname" to nickname,
//                            "mbti" to mbti
//                        )
//                    )
//                }
//                (context as? Activity)?.setResult(RESULT_OK, intent)
//                (context as? Activity)?.startActivity(intent)
//            },
//            modifier = Modifier.fillMaxWidth(),
//        ) {
//            Text("회원가입 하기")
//        }
//        Spacer(modifier = Modifier.padding(bottom = 30.dp))
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//private fun PreviewLogin() {
//    SignUpScreen()
//}