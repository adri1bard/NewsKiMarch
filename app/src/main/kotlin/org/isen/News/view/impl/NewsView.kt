package org.isen.News.view.impl

import org.isen.News.controller.NewsController
import org.isen.News.view.INewsView
import java.beans.PropertyChangeEvent

class NewsView(var controller: NewsController): INewsView {

    init {
        controller.addView(this)
    }

    override fun displayNews(news: List<String>) {
        println("NewsView: $news")
    }

    override fun propertyChange(evt: PropertyChangeEvent?) {
        println(evt.toString())
    }


}