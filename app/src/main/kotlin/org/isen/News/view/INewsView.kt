package org.isen.News.view

import java.awt.event.ActionListener
import java.beans.PropertyChangeListener

interface INewsView:  PropertyChangeListener, ActionListener {
    fun display()
    fun close()


}