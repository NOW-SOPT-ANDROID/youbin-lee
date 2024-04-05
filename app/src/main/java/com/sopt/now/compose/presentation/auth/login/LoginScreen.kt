//package com.sopt.now.compose.presentation.auth.login
//
//import android.app.Activity.RESULT_OK
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.compose.setContent
//import androidx.activity.result.contract.ActivityResultContracts
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
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.core.os.bundleOf
//import com.sopt.now.compose.presentation.auth.signup.SignUpActivity
//import com.sopt.now.compose.presentation.mypage.MyPageActivity
//import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
//
//class LoginActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            NOWSOPTAndroidTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    LoginScreen()
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun LoginScreen(
//) {
//    var loginId by remember { mutableStateOf("") }
//    var loginPassword by remember { mutableStateOf("") }
//    var id by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var nickname by remember { mutableStateOf("") }
//    var mbti by remember { mutableStateOf("") }
//
//    val context = LocalContext.current
//    val getResult =
//        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == RESULT_OK) {
//                id = result.data?.getStringExtra("id").orEmpty()
//                password = result.data?.getStringExtra("password").orEmpty()
//                nickname = result.data?.getStringExtra("nickname").orEmpty()
//                mbti = result.data?.getStringExtra("mbti").orEmpty()
//            }
//        }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 40.dp)
//    ) {
//        Spacer(modifier = Modifier.padding(20.dp))
//        Text(
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            text = "Welcome To SOPT",
//            fontSize = 25.sp
//        )
//        Spacer(modifier = Modifier.weight(1f))
//        Text(text = "ID")
//        TextField(
//            value = loginId,
//            onValueChange = { loginId = it },
//            modifier = Modifier.fillMaxWidth(),
//            placeholder = { Text("사용자 이름 입력") }
//        )
//        Spacer(modifier = Modifier.padding(vertical = 20.dp))
//        Text("비밀번호")
//        TextField(
//            value = loginPassword,
//            onValueChange = { loginPassword = it },
//            modifier = Modifier.fillMaxWidth(),
//            placeholder = { Text("비밀번호 입력") }
//        )
//        Spacer(modifier = Modifier.weight(2f))
//        Button(
//            onClick = {
//                if (loginId.isNotEmpty() && loginId == id && loginPassword.isNotEmpty() && loginPassword == password
//                ) {
//                    Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(context, MyPageActivity::class.java).apply {
//                        putExtras(
//                            bundleOf(
//                                "id" to id,
//                                "pw" to password,
//                                "nickname" to nickname,
//                                "mbti" to mbti
//                            )
//                        )
//                    }
//                    context.startActivity(intent)
//                } else Toast.makeText(context, "ID 혹은 비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show()
//            },
//            modifier = Modifier.fillMaxWidth(),
//        ) {
//            Text("로그인 하기")
//        }
//        Spacer(modifier = Modifier.padding(vertical = 5.dp))
//
//        Button(
//            onClick = { getResult.launch(Intent(context, SignUpActivity::class.java)) },
//            modifier = Modifier.fillMaxWidth()
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
//    LoginScreen()
//}