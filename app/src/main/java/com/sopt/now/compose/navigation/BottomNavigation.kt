package com.sopt.now.compose.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavigation(
    isVisible: Boolean,
    items: List<BottomNavigationItem>,
    currentItem: BottomNavigationItem?,
    onItemSelected: (BottomNavigationItem) -> Unit
) {
    AnimatedVisibility(visible = isVisible) {
        NavigationBar {
            items.forEach { itemType ->
                NavigationBarItem(
                    selected = currentItem == itemType,
                    onClick = {
                        onItemSelected(itemType)
                    },
                    icon = {
                        Icon(
                            itemType.icon,
                            contentDescription = stringResource(id = itemType.title)
                        )
                    },
                    label = { Text(stringResource(id = itemType.title), fontSize = 9.sp) },
                )
            }
        }
    }
}