package com.sopt.now.compose.feature.main.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchRoute() {

    SearchScreen()
}

@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {
        Text(text = "This is SearchScreen")
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    SearchScreen()
}