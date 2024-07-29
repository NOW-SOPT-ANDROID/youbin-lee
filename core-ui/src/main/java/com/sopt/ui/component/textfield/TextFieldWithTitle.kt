package com.sopt.feature.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextFieldWithTitle(
    text: String,
    value: String,
    placeholder: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
    ) {
    Text(text = text)
    TextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(text = placeholder) }
    )
}