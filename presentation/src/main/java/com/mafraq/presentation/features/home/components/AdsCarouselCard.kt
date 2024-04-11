package com.mafraq.presentation.features.home.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.tv.material3.Carousel
import androidx.tv.material3.CarouselState
import androidx.tv.material3.rememberCarouselState
import coil.compose.AsyncImage
import com.mafraq.data.entities.home.Ad
import com.mafraq.presentation.design.theme.MafraqTheme



@Composable
fun AdsCarouselCard(ads: List<Ad>, onClick: (Int) -> Unit) {
    var showMore by remember { mutableStateOf(false) }
    val configurations = LocalConfiguration.current
    val cardHeight = with(LocalDensity.current) {
        (configurations.screenHeightDp / 1.65f).toDp()
    }
    val carouselState: CarouselState = rememberCarouselState()
    Carousel(
        itemCount = ads.size,
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .clip(MafraqTheme.shapes.medium),
        carouselState = carouselState,
        carouselIndicator = {
            IndicatorRow(
                itemCount = ads.size,
                activeItemIndex = carouselState.activeItemIndex,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(MafraqTheme.sizes.medium),
            )
        }
    ) { index ->
        val adModel = ads[index]
        CarouselItemBackground(adModel = adModel, modifier = Modifier.fillMaxSize())
        CarouselItemForeground(
            adModel = adModel,
            showMore = showMore,
            modifier = Modifier.fillMaxSize(),
            onClick = {
                onClick(index)
                showMore = !showMore
            }
        )
    }
}

@Composable
private fun CarouselItemForeground(
    adModel: Ad,
    modifier: Modifier = Modifier,
    showMore: Boolean = false,
    onClick: () -> Unit
) {
    val shadow = remember {
        Shadow(
            color = Color.Black.copy(alpha = 0.5f),
            offset = Offset(x = 2f, y = 4f),
            blurRadius = 2f
        )
    }

    val maxLines by animateIntAsState (
        if (showMore) 5 else 1,
        label = "maxLines"
    )

    Box(
        modifier = modifier.clickable(onClick=onClick),
        contentAlignment = Alignment.BottomStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MafraqTheme.sizes.large),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = adModel.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MafraqTheme.colors.surface,
                    shadow = shadow,
                    textDirection = TextDirection.Content
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = maxLines,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = adModel.description,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MafraqTheme.colors.onPrimary.copy(
                        alpha = 0.65f
                    ),
                    shadow = shadow,
                    textDirection = TextDirection.Content
                ),
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MafraqTheme.sizes.small)
            )
        }
    }
}

@Composable
private fun CarouselItemBackground(
    adModel: Ad,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = adModel.imageUrl,
        contentDescription = null,
        modifier = modifier
            .drawWithContent {
                drawContent()
                drawRect(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.5f)
                        )
                    )
                )
            },
        contentScale = ContentScale.Crop
    )
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

