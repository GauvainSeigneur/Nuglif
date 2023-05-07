package lapresse.presentation.model

import lapresse.presentation.action.UiAction

data class FilterUiModel(
    val image: ImageUiModel.Local,
    val clickAction: UiAction.NoArgs,
)
