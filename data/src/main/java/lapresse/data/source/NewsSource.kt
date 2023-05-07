package lapresse.data.source

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import lapresse.data.ArticleDataItem
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayList

/**
 * Mock a remote data service 
 */
class NewsSource {

 fun getNews() {
         /*try {
             val articles = resources.openRawResource(R.raw.articles)
             val reader = BufferedReader(InputStreamReader(articles, "UTF-8"))
             val listType = object : TypeToken<ArrayList<ArticleDataItem>>() {}.type
             return Gson().fromJson(reader, listType)
         } catch (e: Exception) {
             throw RuntimeException(e)
         }*/
     }
}