package org.isen.News.controller

import org.isen.News.model.INewsModel
import org.isen.News.model.impl.NewsModel
import org.isen.News.view.INewsView

class NewsController(val model: NewsModel) {
    val views = mutableListOf<INewsView>()
    fun registerView(view: INewsView, canal:String?=null){
        this.views.add(view)
        if(canal == null){
            model.addListener(INewsModel.DATATYPE_ARTICLE,view)

        }
        else model.addListener(canal ,view)
    }

    fun displayViews(){
        views[0].display()

    }

    fun closeViews(){
        views.forEach(){
            it.close()
        }
    }

    fun loadStationInformation(){
        model.getNewsByTopic()
    }
    fun changeSelectedArticle(author: String, title: String){
        this.model.changeSelectedArticle(author, title)
    }

    fun displayDetails(){
        views[1].display()
    }

}