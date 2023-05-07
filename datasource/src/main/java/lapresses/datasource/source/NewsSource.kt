package lapresses.datasource.source

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import lapresses.datasource.R
import lapresses.datasource.ArticleDataItem
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayList
import javax.inject.Inject

/**
 * Mock a remote data service
 */
class NewsSource @Inject constructor(@ApplicationContext private val context: Context) {

    fun getNews(): List<ArticleDataItem> {
        val articles = context.resources.openRawResource(R.raw.articles)
        val reader = BufferedReader(InputStreamReader(articles, "UTF-8"))
        val listType = object : TypeToken<ArrayList<ArticleDataItem>>() {}.type
        return Gson().fromJson(reader, listType)
    }
}