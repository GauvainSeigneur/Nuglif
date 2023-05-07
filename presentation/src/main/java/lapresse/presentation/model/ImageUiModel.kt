package lapresse.presentation.model

sealed interface ImageUiModel {
    data class Local(val data: LocalImageUiModel) : ImageUiModel
    data class Remote(val url: String, val fallback: LocalImageUiModel) : ImageUiModel
}