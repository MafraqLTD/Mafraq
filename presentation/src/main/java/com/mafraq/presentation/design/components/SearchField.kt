package com.mafraq.presentation.design.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mafraq.presentation.R
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.clickableNoRipple
import com.mafraq.presentation.utils.extensions.optionalComposable
import com.mafraq.presentation.utils.extensions.painter

@Composable
fun SearchField(
    query: String,
    placeholder: String? = null,
    onQueryChange: (String) -> Unit,
    onClear: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    var active by rememberSaveable { mutableStateOf(false) }
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = { active = false },
        active = active,
        onActiveChange = { active = it },
        shape = MafraqTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { active = it.hasFocus },
        placeholder = placeholder?.optionalComposable {
            Placeholder(text = placeholder, textAlign = TextAlign.Start)
        },
        leadingIcon = {
            Icon(
                painter = R.drawable.ic_search.painter,
                contentDescription = null,
                tint = MafraqTheme.colors.primary,
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = optionalComposable(shouldExecute = active) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null,
                modifier = Modifier.clickableNoRipple(onClick = onClear)
            )
        },
        shadowElevation = 1.dp,
        colors = SearchBarDefaults.colors(
            dividerColor = MafraqTheme.colors.divider,
            containerColor = MafraqTheme.colors.onPrimary,
        ),
        content = content
    )
}