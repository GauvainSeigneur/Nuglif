package lapresse.nuglif

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import dagger.hilt.android.AndroidEntryPoint
import lapresse.nuglif.ui.theme.NuglifTheme

@AndroidEntryPoint
class NewsListActivity : ComponentActivity() {

    private val viewModel: NewsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NuglifTheme {
                NewsScreen(uiModel = viewModel.uiModel.collectAsState().value)
            }
        }
    }
}