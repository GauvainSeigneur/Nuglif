package lapresses.datasource

data class ArticleDataItem(
    val id: String?,
    val channelName: String?,
    val title: String?,
    val publicationDate: String?,
    val visual: List<VisualDataItem>,
)
