package org.isen.News.model.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import java.net.URL

data class Source(
    val id: String?,//nullable
    val name: String
)
data class ArticleData(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: URL,
    val urlToImage: URL,
    val publishedAt: String,
    val content: String
)

data class Article(val articles: List<ArticleData>, val totalresults:Long, val status:String){
    class Deserializer: ResponseDeserializable<Article> {
        override fun deserialize(content: String): Article = Gson().fromJson(content, Article::class.java)
    }
}