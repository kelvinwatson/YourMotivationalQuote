package com.example.ymq.nav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable

@Composable
fun QBottomNavigationBar(content: @Composable RowScope.() -> Unit) {
    NavigationBar(
        content = content
    )
}