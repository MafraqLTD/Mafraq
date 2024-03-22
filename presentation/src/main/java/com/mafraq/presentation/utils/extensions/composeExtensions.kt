package com.mafraq.presentation.utils.extensions

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.PressGestureScope
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalAutofillTree
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import com.mafraq.presentation.design.components.placeholder.PlaceholderHighlight
import com.mafraq.presentation.design.components.placeholder.placeholder
import com.mafraq.presentation.design.components.placeholder.shimmer


fun Modifier.clickableNoRipple(
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
) = composed {
    this.clickable(
        interactionSource = interactionSource ?: remember { MutableInteractionSource() },
        indication = indication,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick,
    )
}

/**
 * used to get string value from resources
 * @author Ahmed Mones
 * */
val @receiver:StringRes Int.string: String
    @Composable
    get() = if (this == 0) "" else stringResource(id = this)

/**
 * used to create Painter instance from resources
 * @author Ahmed Mones
 * */
val @receiver:DrawableRes Int.painter: Painter
    @Composable
    get() = painterResource(id = this)

val @receiver:DrawableRes Int.painterOrNull: Painter?
    @Composable
    get() = if (this == 0) null else painterResource(id = this)

private val NoPressGesture: suspend PressGestureScope.(Offset) -> Unit = { }

fun Modifier.detectTapGestures(
    onDoubleTap: ((Offset) -> Unit)? = null,
    onLongPress: ((Offset) -> Unit)? = null,
    onPress: suspend PressGestureScope.(Offset) -> Unit = NoPressGesture,
    onTap: ((Offset) -> Unit)? = null
) = this then Modifier.pointerInput(Unit) {
    this.detectTapGestures(
        onDoubleTap = onDoubleTap,
        onLongPress = onLongPress,
        onPress = onPress,
        onTap = onTap
    )
}


@Stable
@Immutable
data class RichText(
    val subText: String,
    val style: SpanStyle,
)

fun AnnotatedString.Builder.addStyle(style: SpanStyle, text: String, regexp: Regex) {
    for (result in regexp.findAll(text)) {
        addStyle(style, result.range.first, result.range.last + 1)
    }
}

fun AnnotatedString.Builder.addStyle(style: SpanStyle, text: String, regexp: String) {
    addStyle(style, text, Regex.fromLiteral(regexp))
}

fun String.toRichText(vararg richTexts: RichText): AnnotatedString {
    return buildAnnotatedString {
        append(this@toRichText)

        for (richText in richTexts)
            addStyle(
                style = richText.style,
                text = this@toRichText,
                regexp = richText.subText
            )
    }
}

fun Modifier.placeholderShimmer(
    visible: Boolean = true,
    color: Color = Color.Unspecified,
    shape: Shape? = null,
    highlight: PlaceholderHighlight? = null,
    placeholderFadeTransitionSpec: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Float> = { spring() },
    contentFadeTransitionSpec: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Float> = { spring() },
): Modifier = composed {
    this.placeholder(
        visible = visible,
        color = if (color.isSpecified)
            color
        else
            MaterialTheme.colorScheme.surfaceColorAtElevation(NavigationBarDefaults.Elevation),
        shape = shape ?: MaterialTheme.shapes.small,
        highlight = highlight ?: PlaceholderHighlight.shimmer(),
        placeholderFadeTransitionSpec = placeholderFadeTransitionSpec,
        contentFadeTransitionSpec = contentFadeTransitionSpec,
    )
}
