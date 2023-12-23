package org.isen.News.model.impl

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates

class ArticleModel {
    var pcs = PropertyChangeSupport(this)
    var nb:Int by Delegates.observable(0) {
        prop, old, new ->
        pcs.firePropertyChange(prop.name, old, new)
    }

    fun register(listener: PropertyChangeListener) {
        pcs.addPropertyChangeListener(listener)
    }
    fun researchbyAuthor(author: String) {
        println("Research by author: $author")
        
    }
}