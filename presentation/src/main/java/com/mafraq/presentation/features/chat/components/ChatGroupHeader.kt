package com.mafraq.presentation.features.chat.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mafraq.presentation.R
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.features.chat.support.ChatSupportInteractionListener.Preview.onNavigateBack
import com.mafraq.presentation.utils.extensions.optionalComposable
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string


@Composable
fun ChatGroupHeader(
    title: String,
    members: Int,
    activeMembers: Int,
    showBackButton: Boolean = false,
    onNavigateBack: () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    TopAppBar(
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = if (showBackButton) -sizes.medium else sizes.default),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = MafraqTheme.typography.titleLarge
                )

                Text(
                    text = R.string.members_with_arg.string(members, activeMembers),
                    style = MafraqTheme.typography.label
                )
            }
        },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (showBackButton)
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        painter = R.drawable.ic_back_arrow.painter,
                        contentDescription = null
                    )
                }
        }
    )
}
