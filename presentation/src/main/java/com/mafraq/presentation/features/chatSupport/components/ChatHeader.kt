package com.mafraq.presentation.features.chatSupport.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mafraq.presentation.R
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.painter

@Composable
fun ChatHeader(
    title: String,
    isUserActive: Boolean,
    onNavigateBack: () -> Unit,
) {
    val statusCircleSize = 12.dp
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val statusColor by animateColorAsState(
        targetValue = if (isUserActive) Color.Green else Color.Gray,
        label = "statusColor"
    )
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .offset(x = -statusCircleSize)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    space = MafraqTheme.sizes.small,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                Text(text = title)

                Badge(
                    containerColor = statusColor,
                    modifier = Modifier.size(statusCircleSize),
                    content = {}
                )
            }
        },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    painter = R.drawable.ic_back_arrow.painter,
                    contentDescription = null
                )
            }
        }
    )
}