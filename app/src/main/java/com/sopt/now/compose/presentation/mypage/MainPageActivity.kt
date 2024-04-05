package com.sopt.now.compose.presentation.mypage


import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class MainPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainPageScreen()
                }
            }
        }
    }
}

@Composable
fun MainPageScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_main_compose),
                contentDescription = "img_main_compose",
                modifier = Modifier
                    .size(60.dp)
                    .aspectRatio(1f / 1f),
            )
            Text(
                "나솝은 지금부터~~~",
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 10.dp),
            )
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "ID",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        (context as? Activity)?.intent?.getStringExtra("id")?.let {
            Text(
                text = it,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 40.dp),
            )
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "닉네임",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        (context as? Activity)?.intent?.getStringExtra("nickname")?.let {
            Text(
                text = it,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 40.dp),
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    NOWSOPTAndroidTheme {
        MainPageScreen()
    }
}