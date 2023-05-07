package lapresse.domain.entity

data class ArticleEntity(
    val id: String,
    val channelName: String?,
    val title: String?,
    val publicationDate: String?,
    val imageUrl: String,
)
