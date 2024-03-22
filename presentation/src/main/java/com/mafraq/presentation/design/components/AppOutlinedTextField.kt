package com.mafraq.presentation.design.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mafraq.presentation.R
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.clickableNoRipple
import com.mafraq.presentation.utils.extensions.optionalComposable
import com.mafraq.presentation.utils.extensions.painter
import kotlinx.coroutines.launch


@Composable
fun AppOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    enabledTrailingIcon: Boolean = true,
    onTrailingIconClick: (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    errorMessage: String? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    isPassword: Boolean = false,
    passwordMask: Char = '\u2022', // this unicode represent [•]
    textAlign: TextAlign? = TextAlign.Start,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MafraqTheme.shapes.medium,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MafraqTheme.colors.primary,
        unfocusedBorderColor = MafraqTheme.colors.contentBorder,
    )
) {
    var showPassword: Boolean by remember { mutableStateOf(isPassword.not()) }
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    var focused: Boolean by remember { mutableStateOf(false) }
    var hasFocus: Boolean by remember { mutableStateOf(false) }

    Column {
        label?.let {
            Text(
                text = label,
                modifier = Modifier.padding(bottom = MafraqTheme.sizes.small),
                style = MafraqTheme.typography.titleSmall,
            )
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusChanged {
                    hasFocus = it.hasFocus
                    focused = it.isFocused
                    if (focused) coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            placeholder = placeholder?.optionalComposable {
                Placeholder(text = it, textAlign = textAlign)
            },
            visualTransformation = if (showPassword) visualTransformation
            else PasswordVisualTransformation(mask = passwordMask),
            leadingIcon = leadingIcon?.optionalComposable {
                LeadingIcon(
                    icon = leadingIcon, color = if (isError) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.primary
                )
            },
            trailingIcon = if (isPassword) showPassword.optionalComposable {
                TrailingIcon(
                    state = showPassword,
                    onClick = { showPassword = !showPassword }
                )
            } else
                trailingIcon?.optionalComposable {
                    Icon(
                        it,
                        contentDescription = null,
                        tint = if (enabledTrailingIcon)
                            MaterialTheme.colorScheme.primary
                        else
                            colors.disabledPrefixColor,
                        modifier = onTrailingIconClick?.let {
                            Modifier.clickableNoRipple(
                                enabled = enabledTrailingIcon,
                                onClick = onTrailingIconClick
                            )
                        } ?: Modifier
                    )
                },
            prefix = prefix,
            suffix = suffix,
            supportingText = errorMessage?.optionalComposable {
                if (isError)
                    Text(
                        text = it,
                        modifier = Modifier
                            .offset(x = -MafraqTheme.sizes.medium),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.labelMedium
                    )
            },
            isError = isError,
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction,
                keyboardType = keyboardType
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            interactionSource = interactionSource,
            shape = shape,
            colors = colors
        )
    }
}


@Composable
internal fun TrailingIcon(
    state: Boolean,
    onClick: () -> Unit
) {
    val icon by animateIntAsState(
        targetValue = if (state) R.drawable.hide_password
        else R.drawable.show_password, label = "show_password"
    )

    IconButton(onClick = onClick, modifier = Modifier) {
        Icon(
            icon.painter,
            contentDescription = "Visibility",
            tint = Color.Unspecified,
        )
    }
}

@Composable
internal fun Placeholder(text: String, textAlign: TextAlign?) {
    Text(
        text = text,
        textAlign = textAlign,
        modifier = Modifier.fillMaxWidth(),
        color = MafraqTheme.colors.contentBorder
    )
}

@Composable
internal fun LeadingIcon(
    icon: Any,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    if (icon is Painter) Icon(
        painter = icon,
        contentDescription = "Visibility",
        tint = color
    )
    else if (icon is ImageVector) Icon(
        imageVector = icon,
        contentDescription = "Visibility",
        tint = color
    )
}


@Preview(showBackground = true)
@Composable
fun AppOutlinedTextFieldPreview() = ColumnPreview {
    AppOutlinedTextField(
        value = "",
        label = "Username",
        onValueChange = {}
    )

    AppOutlinedTextField(
        value = "",
        label = "Password",
        isPassword = true,
        onValueChange = {}
    )
}
