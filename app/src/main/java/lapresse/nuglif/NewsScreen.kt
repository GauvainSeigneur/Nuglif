package lapresse.nuglif

import android.annotation.SuppressLint
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
import androidx.compose.ui.window.Dialog
import lapresse.presentation.model.NewsUiModel

@Composable
fun NewsScreen(uiModel: NewsUiModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    NewsScaffold(snackbarHostState = snackbarHostState) {
        NewsContent(uiModel)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewsScaffold(
    snackbarHostState: SnackbarHostState,
    content: @Composable (PaddingValues) -> Unit,
) = Scaffold(
    snackbarHost = { SnackbarHost(snackbarHostState) },
    topBar = {},
    bottomBar = {},
    floatingActionButton = {
        FilterFab()
    },
    content = content
)

@Composable
private fun modal() {
    Dialog(onDismissRequest = { }) {
        
    }
}

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
private fun FilterFab() {
    FloatingActionButton(onClick = { /* ... */ }) {
        /* FAB content */
    }
}