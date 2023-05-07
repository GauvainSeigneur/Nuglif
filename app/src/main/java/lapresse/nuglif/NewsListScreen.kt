package lapresse.nuglif

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import lapresse.presentation.model.ImageUiModel
import lapresse.presentation.model.LocalImageUiModel
import lapresse.presentation.model.NewsItemUiModel
import lapresse.presentation.model.NewsUiModel

@Composable
fun NewsScreen(uiModel: NewsUiModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    NewsScaffold(
        snackbarHostState = snackbarHostState,
        uiModel = uiModel
    ) { paddingValue ->
        NewsContent(uiModel, paddingValue)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewsScaffold(
    snackbarHostState: SnackbarHostState,
    uiModel: NewsUiModel,
    content: @Composable (PaddingValues) -> Unit,
) = Scaffold(
    snackbarHost = { SnackbarHost(snackbarHostState) },
    topBar = {
        TopAppBar(
            title = {
                Text("LaPresse")
            },
        )
    },
    bottomBar = {},
    floatingActionButton = {
        FilterFab(uiModel, snackbarHostState)
    },
    content = content
)

@Composable
private fun NewsContent(uiModel: NewsUiModel, paddingValues: PaddingValues) {
    when (uiModel) {
        is NewsUiModel.Success -> {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.padding(paddingValues)
            ) {
                itemsIndexed(uiModel.news) { _, item ->
                    NewsItem(item)
                }
            }
        }

        is NewsUiModel.Loading -> {
            // do something
        }

        is NewsUiModel.Error -> {
            // do something
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewsItem(item: NewsItemUiModel) {
    ElevatedCard(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .fillMaxHeight()

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                AsyncImage(
                    model = item.image.url,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(56.dp)
                )
                Text(
                    text = item.title,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            SuggestionChip(
                onClick = { },
                label = {
                    Text(
                        text = item.channelName
                    )
                }
            )
            Text(
                text = item.publicationDate,
            )
        }

    }
}

@Composable
private fun FilterFab(uiModel: NewsUiModel, snackbarHostState: SnackbarHostState) {
    val coroutineScope = rememberCoroutineScope()
    if (uiModel is NewsUiModel.Success) {
        FloatingActionButton(
            onClick = {
                uiModel.filterUIModel.clickAction.execute()
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        "Liste réordonnée"
                    )
                }
            }
        ) {
            val drawable = when (uiModel.filterUIModel.image.data) {
                LocalImageUiModel.FILTER_DATE -> R.drawable.sort_history
                else -> R.drawable.baseline_sort_by_alpha_24
            }
            Image(
                painter = painterResource(id = drawable),
                contentDescription = "fab",
                contentScale = ContentScale.FillBounds
            )
        }
    }
}