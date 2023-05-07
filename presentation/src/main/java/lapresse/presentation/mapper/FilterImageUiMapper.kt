package lapresse.presentation.mapper

import lapresse.domain.entity.FilterNewsEntity
import lapresse.presentation.model.ImageUiModel
import lapresse.presentation.model.LocalImageUiModel
import javax.inject.Inject

class FilterImageUiMapper @Inject constructor() {
    fun toUiModel(item: FilterNewsEntity): ImageUiModel.Local {
        val data = when (item) {
            FilterNewsEntity.DATE -> LocalImageUiModel.FILTER_DATE
            FilterNewsEntity.CHANNEL -> LocalImageUiModel.FILTER_CHANNEL
        }
        return ImageUiModel.Local(data = data)
    }
}