package com.mafraq.presentation.features.chat.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mafraq.presentation.R
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.string


@Composable
fun ChatGroupHeader(
    title: String,
    members: Int,
    activeMembers: Int
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    TopAppBar(
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
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
        navigationIcon = {}
    )
}
