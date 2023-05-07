package lapresse.data

data class VisualDataItem(
    val id: String,
    val className: String,
    val urlPattern: String,
    val visual: List<VisualDataItem>,
)