package app.lawnchair.lawnicons.ui.components.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.lawnchair.lawnicons.model.IconInfo
import app.lawnchair.lawnicons.ui.theme.LawniconsTheme
import app.lawnchair.lawnicons.ui.util.PreviewLawnicons
import app.lawnchair.lawnicons.ui.util.SampleData
import app.lawnchair.lawnicons.ui.util.toPaddingValues
import kotlinx.collections.immutable.ImmutableList
import my.nanihadesuka.compose.LazyVerticalGridScrollbar
import my.nanihadesuka.compose.ScrollbarSelectionMode
import my.nanihadesuka.compose.ScrollbarSettings

@Composable
@ExperimentalFoundationApi
fun IconPreviewGrid(
    iconInfo: ImmutableList<IconInfo>,
    isExpandedScreen: Boolean,
    onSendResult: (IconInfo) -> Unit,
    modifier: Modifier = Modifier,
    isIconPicker: Boolean = false,
    contentPadding: PaddingValues? = null,
    listState: LazyGridState = rememberLazyGridState(),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth(),
    ) {
        LazyVerticalGridScrollbar(
            modifier = Modifier
                .then(
                    if (isExpandedScreen) Modifier.width(640.dp) else Modifier,
                )
                .statusBarsPadding()
                .padding(top = 26.dp),
            state = listState,
            settings = ScrollbarSettings(
                alwaysShowScrollbar = true,
                thumbUnselectedColor = MaterialTheme.colorScheme.primaryContainer,
                thumbSelectedColor = MaterialTheme.colorScheme.primary,
                selectionMode = ScrollbarSelectionMode.Thumb,
            ),
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 80.dp),
                contentPadding = contentPadding ?: if (!isExpandedScreen) {
                    WindowInsets.navigationBars.toPaddingValues(
                        additionalStart = 8.dp,
                        additionalTop = 42.dp,
                        additionalEnd = 8.dp,
                    )
                } else {
                    WindowInsets.navigationBars.toPaddingValues(
                        additionalStart = 32.dp,
                        additionalTop = 42.dp,
                        additionalEnd = 32.dp,
                    )
                },
                state = listState
            ) {
                items(items = iconInfo) { iconInfo ->
                    IconPreview(
                        iconInfo = iconInfo,
                        isIconPicker = isIconPicker,
                        onSendResult = onSendResult,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@PreviewLawnicons
@Composable
private fun IconGridPreview() {
    LawniconsTheme {
        IconPreviewGrid(
            SampleData.iconInfoList,
            true,
            {},
            Modifier,
            false,
        )
    }
}
