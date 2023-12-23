package org.isen.News.view

import java.beans.PropertyChangeListener

interface INewsView: PropertyChangeListener {
    fun displayNews(news: List<String>)

}