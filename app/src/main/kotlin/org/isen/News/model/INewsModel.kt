package org.isen.News.model

import java.beans.PropertyChangeListener

interface INewsModel {
    companion object{
        const val DATATYPE_ARTICLE = "String"
    }
    fun getNewsByTopic()
    fun addListener(canal:String, listener: PropertyChangeListener)
    fun deleteListener(listener: PropertyChangeListener)
    fun changeSelectedArticle(author: String, title: String)
    fun getArticle()
}