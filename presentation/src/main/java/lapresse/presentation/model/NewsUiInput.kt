package lapresse.presentation.model

import lapresse.domain.entity.FilterNewsEntity

sealed interface NewsUiInput {
    data class Click(val id: String) : NewsUiInput
    data class Filter(val type: FilterNewsEntity) : NewsUiInput
}