package lapresse.nuglif

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import lapresse.presentation.model.ImageUiModel
import lapresse.presentation.model.LocalImageUiModel
import lapresse.presentation.model.NewsUiModel

@Composable
fun NewsScreen(uiModel: NewsUiModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    NewsScaffold(
        snackbarHostState = snackbarHostState,
        uiModel = uiModel
    ) {
        NewsContent(uiModel)
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
    topBar = {},
    bottomBar = {},
    floatingActionButton = {
        FilterFab(uiModel)
    },
    content = content
)

@Composable
private fun NewsContent(uiModel: NewsUiModel) {
    when (uiModel) {
        is NewsUiModel.Success -> {
            LazyColumn {
                itemsIndexed(uiModel.news) { index, item ->
                    Text(
                        text = item.title,
                    )
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

@Composable
private fun FilterFab(uiModel: NewsUiModel) {
    if (uiModel is NewsUiModel.Success) {
        FloatingActionButton(onClick = { /* ... */ }) {
            val drawable = when (val imageModel = uiModel.filterUIModel) {
                is ImageUiModel.Local -> {
                    when (imageModel.data) {
                        LocalImageUiModel.FILTER_CHANNEL -> R.drawable.baseline_sort_by_alpha_24
                        LocalImageUiModel.FILTER_DATE -> R.drawable.sort_history
                    }
                }
                is ImageUiModel.Remote -> R.drawable.baseline_sort_by_alpha_24
            }
            // todo make a controller to handle each type of ImageUiModel
            Image(
                painter = painterResource(id = drawable),
                contentDescription = "fab",
                contentScale = ContentScale.FillBounds
            )
        }
    }
}