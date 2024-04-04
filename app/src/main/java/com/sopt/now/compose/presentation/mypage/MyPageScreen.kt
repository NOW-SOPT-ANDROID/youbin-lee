package com.sopt.now.compose.presentation.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.sopt.now.compose.R

@Composable
fun MyPageScreen() {
    val userId by remember { mutableStateOf("") }
    val password by remember { mutableStateOf("") }
    val nickname by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                imageModel = { R.drawable.img_main_compose },
                modifier = Modifier
                    .size(30.dp)
            )
            Text(text = nickname)
        }

        Spacer(modifier = Modifier.padding(20.dp))
        Text(text = "나솝은 지금부터~~~")
        Spacer(modifier = Modifier.padding(20.dp))
        Text(text = "ID")
        Text(text = userId)
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text("비밀번호")
        Text(text = password)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMainScreen() {
    MyPageScreen()
}