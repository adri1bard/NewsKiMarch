package org.isen.News.model.impl

import com.github.kittinunf.fuel.httpGet
import org.isen.News.model.INewsModel
import org.isen.News.model.data.Article
import org.isen.News.model.data.ArticleData
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates

class NewsModel: INewsModel {
    private var everythingURL:String = "https://newsapi.org/v2/everything"
    private var search_topic:String= "q=tesla"
    private var apiKey:String = "apiKey=d46c0fcca35843d481d37e7b3668f168"

    var pcs = PropertyChangeSupport(this)
    override fun addListener(canal: String, listener: PropertyChangeListener) {
        pcs.addPropertyChangeListener(canal, listener)
    }
    override fun deleteListener(listener: PropertyChangeListener) {
        pcs.removePropertyChangeListener(listener)
    }




    private var article:Article? by Delegates.observable(null){
        prop, old, new ->
        pcs.firePropertyChange(INewsModel.DATATYPE_ARTICLE, old, new)
    }




    override fun getNewsByTopic() {
        var URL_topic = everythingURL + "?" + search_topic + "&" + apiKey
        URL_topic.httpGet().responseObject(Article.Deserializer()){
            request, response, result ->
            result.let{
                (si,error) ->
                if(si != null) {
                    this.article = si
                } else {
                    println("error because News list must not be null : $error")
                }
            }

        }
    }



    private var selectedArticle: ArticleData? by Delegates.observable(null){
            property, oldValue, newValue ->  pcs.firePropertyChange(INewsModel.DATATYPE_ARTICLE, oldValue, newValue)
    }
    private var articleList = listOf<ArticleData>()
    override fun changeSelectedArticle(author: String, title: String){
        if (articleList.isEmpty()){
            this.getArticle()
        }
        selectedArticle = articleList.find { it.author == author && it.title == title}
    }
    override fun getArticle(){
        var URL_topic = everythingURL + "?" + search_topic + "&" + apiKey
        URL_topic.httpGet().responseObject(Article.Deserializer()){ request, response, result ->
            result.let { (si,error) ->
                if (si != null){
                    this.articleList = si?.articles?: listOf()
                }
                else println("error : $error")
            }
        }
    }
}