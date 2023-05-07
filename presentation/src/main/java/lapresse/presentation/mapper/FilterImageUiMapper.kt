package lapresse.presentation.mapper

import lapresse.domain.entity.FilterNewsEntity
import lapresse.presentation.action.ActionEvent
import lapresse.presentation.action.UiInputHandler
import lapresse.presentation.model.FilterUiModel
import lapresse.presentation.model.ImageUiModel
import lapresse.presentation.model.LocalImageUiModel
import lapresse.presentation.model.NewsUiInput
import javax.inject.Inject

class FilterImageUiMapper @Inject constructor() {
    fun toUiModel(item: FilterNewsEntity, uiInputHandler: UiInputHandler<NewsUiInput>): FilterUiModel {
        val imageData = when (item) {
            FilterNewsEntity.DATE -> LocalImageUiModel.FILTER_CHANNEL
            FilterNewsEntity.CHANNEL -> LocalImageUiModel.FILTER_DATE
        }

        return FilterUiModel(
            image = ImageUiModel.Local(imageData),
            clickAction = ActionEvent(
                uiInputHandler = uiInputHandler,
                event = NewsUiInput.Filter(item)
            ),
        )
    }
}