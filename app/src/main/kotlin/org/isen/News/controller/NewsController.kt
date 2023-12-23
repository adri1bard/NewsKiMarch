package org.isen.News.controller

import org.isen.News.model.impl.ArticleModel
import org.isen.News.view.INewsView

class NewsController(val model: ArticleModel) {
    val views = mutableListOf<INewsView>()

    fun addView(v: INewsView) {
        views.add(v)
        model.register(v)
    }
}