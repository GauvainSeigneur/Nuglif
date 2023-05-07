package lapresse.presentation.model

data class NewsItemUiModel(
    val id: String,
    val title: String,
    val channelName: String,
    val publicationDate: String,
    val image: ImageUiModel.Remote
)