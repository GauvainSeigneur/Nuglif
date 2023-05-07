package lapresses.datasource

import lapresse.domain.entity.ArticleEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class ArticleEntityMapperTests {

    private val mapper = ArticleEntityMapper()

    @Test
    fun `Given article response with null id, when mapper is invoked, then it should return null`() {
        // Given
        val givenUrl = "http://imageUrl"
        val givenVisualDataItem = VisualDataItem("id", "className", givenUrl)
        val givenArticleDataItemA = ArticleDataItem(
            id = null,
            title = "title_A",
            channelName = "channelName_A",
            publicationDate = "publicationDate_A",
            visual = emptyList()
        )
        val givenArticleDataItemB = ArticleDataItem(
            id = "id_B",
            title = "title_B",
            channelName = "channelName_B",
            publicationDate = "publicationDate_B",
            visual = emptyList(),
        )
        val givenArticleDataItemC = ArticleDataItem(
            id = "id_C",
            title = "title_C",
            channelName = "channelName_C",
            publicationDate = null,
            visual = listOf(givenVisualDataItem)
        )
        val givenDataList = listOf(givenArticleDataItemA, givenArticleDataItemB, givenArticleDataItemC)
        // when
        val actual = mapper.toEntities(givenDataList)
        val expectedItemB = getEntity("B")
        val expectedItemC = getEntity("C").copy(
            publicationDate = null,
            imageUrl = "https://imageUrl"
        )
        val expected = listOf(expectedItemB, expectedItemC)
        assertEquals(expected, actual)
    }

    private fun getEntity(item: String) = ArticleEntity(
        id = "id_$item",
        title = "title_$item",
        channelName = "channelName_$item",
        publicationDate = "publicationDate_$item",
        imageUrl = "https://via.placeholder.com/600x400"
    )
}

