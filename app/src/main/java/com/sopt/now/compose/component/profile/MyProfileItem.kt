package com.sopt.now.compose.component.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyProfileItem(
    name: String,
    profileImage: Int,
    profileImageEtc: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(profileImage),
            contentDescription = "img_profile",
            modifier = Modifier
                .size(100.dp)
                .aspectRatio(1f / 1f),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(10.dp))
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(profileImageEtc),
            contentDescription = "img_profile_etc",
            modifier = Modifier
                .size(60.dp)
                .aspectRatio(1f / 1f),
        )
    }
}
