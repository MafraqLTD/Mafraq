package com.mafraq.presentation.design.components.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string


@Composable
fun VerificationStatus(
    verified: Boolean,
    onDoneClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.animateContentSize()) {
        AnimatedVisibility(
            visible = verified.not(),
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .background(MaterialTheme.colorScheme.secondary)
                    .fillMaxWidth()
                    .padding(sizes.small),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = R.drawable.ic_verified.painter,
                        contentDescription = "Verification Icon",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )

                    Spacer.Small(vertical = false)

                    Text(
                        text = R.string.please_check_your_email.string,
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                    Surface(
                        onClick = onDoneClicked,
                        color = Color.Unspecified,
                        shape = MaterialTheme.shapes.large,
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onSecondary
                        ),
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Text(
                            text = R.string.done.string,
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(
                                horizontal = sizes.small,
                                vertical = sizes.extraSmall
                            )
                        )
                    }

            }
        }
        content()
    }
}