package com.mafraq.presentation.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.util.lerp
import com.mafraq.presentation.design.theme.MafraqTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.max


data class CarouselItem<T>(
    val items: List<T>,
    val itemCount: Int = items.size,
)


@Composable
fun <T> Carousel(
    value: CarouselItem<T>,
    modifier: Modifier = Modifier,
    state: PagerState = rememberPagerState { value.itemCount },
    autoScrollDuration: Long = 3000L,
    indicatorPadding: PaddingValues = PaddingValues(vertical = MafraqTheme.sizes.medium),
    indicatorAlignment: Alignment = Alignment.BottomCenter,
    indicator: @Composable (itemCount: Int, activeItemIndex: Int) -> Unit = { itemCount, activeItemIndex ->
        IndicatorRow(
            itemCount = itemCount,
            activeItemIndex = activeItemIndex,
            modifier = Modifier.padding(indicatorPadding)
        )
    },
    content: @Composable (BoxScope.(index: Int, item: T, modifier: Modifier) -> Unit)
) {
    val isDragged by state.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(state) {
            var currentPageKey by remember { mutableIntStateOf(0) }
            LaunchedEffect(key1 = currentPageKey) {
                launch {
                    delay(timeMillis = autoScrollDuration)
                    val nextPage = (currentPage + 1).mod(max(1, pageCount))
                    animateScrollToPage(page = nextPage)
                    currentPageKey = nextPage
                }
            }
        }
    }

    Box(modifier = modifier, contentAlignment = indicatorAlignment) {
        HorizontalPager(
            state = state,
            modifier = modifier
        ) { index ->
            val item = value.items[index]
            Box(modifier = modifier) {
                content(
                    index,
                    item,
                    Modifier.carouselTransition(index, state)
                )
            }
        }

        indicator(value.itemCount, state.currentPage)
    }
}

@Composable
internal fun IndicatorRow(
    itemCount: Int,
    activeItemIndex: Int,
    modifier: Modifier = Modifier,
    spacing: Dp = MafraqTheme.sizes.small,
    indicator: @Composable (isActive: Boolean) -> Unit = { isActive ->
        val activeColor = Color.White
        val inactiveColor = activeColor.copy(alpha = 0.3f)
        Box(
            modifier = Modifier
                .size(MafraqTheme.sizes.small)
                .background(
                    color = if (isActive) activeColor else inactiveColor,
                    shape = MafraqTheme.shapes.extraSmall,
                ),
        )
    }
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        repeat(itemCount) {
            val isActive = it == activeItemIndex
            indicator(isActive)
        }
    }
}


fun Modifier.carouselTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val transformation =
            lerp(
                start = 0.7f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )
        alpha = transformation
        scaleY = transformation
    }
