package org.isen.News.model.data

import com.github.kittinunf.fuel.core.isSuccessful
import com.github.kittinunf.fuel.httpGet
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ArticlesTest{
    @Test
    fun getNumberOfArticlesFromJson(){
        var url= "https://newsapi.org/v2/everything?q=tesla&sortBy=publishedAt&apiKey=d46c0fcca35843d481d37e7b3668f168"
        val (request, response, result) = url.httpGet().responseObject(
            Article.Deserializer())
        assertTrue(response.isSuccessful)
        val (si, error) = result
        if (si != null) {
            assertEquals("Hürriyet", si.articles[3].author)
        } else {
            fail("error because News list must not be null : $error")
        }
    }
}