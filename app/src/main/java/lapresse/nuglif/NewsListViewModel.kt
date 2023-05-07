package lapresse.nuglif

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import lapresse.presentation.model.NewsUiModel
import lapresse.presentation.GetNewsViewModelFactory
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    getNewsViewModelFactory: GetNewsViewModelFactory,
) : ViewModel() {

    private val getNewsViewModel = getNewsViewModelFactory.create(viewModelScope = viewModelScope)

    val uiModel: StateFlow<NewsUiModel> = getNewsViewModel.uiModel
}