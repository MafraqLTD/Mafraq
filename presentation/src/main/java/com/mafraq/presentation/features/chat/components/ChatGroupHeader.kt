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
    connectedMembers: Int
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

                if (connectedMembers > 0)
                    Text(
                        text = R.string.members.string
                            .format(members, connectedMembers),
                        style = MafraqTheme.typography.label
                    )
            }
        },
        scrollBehavior = scrollBehavior,
        navigationIcon = {}
    )
}
