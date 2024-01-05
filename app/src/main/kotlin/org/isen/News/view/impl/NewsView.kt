package org.isen.News.view.impl

import org.isen.News.controller.NewsController
import org.isen.News.model.INewsModel
import org.isen.News.model.impl.NewsModel
import org.isen.News.model.data.Article
import org.isen.News.model.data.ArticleData
import org.isen.News.view.INewsView
import java.beans.PropertyChangeEvent
import javax.swing.WindowConstants.EXIT_ON_CLOSE
import java.awt.BorderLayout
import java.awt.GridLayout
import java.awt.event.ActionEvent
import javax.swing.*

class NewsView(var controller: NewsController): INewsView, JFrame("News"){
    private val labelName = JLabel()
    private val articleLabel = JLabel()
    private var NewsList:JComboBox<ArticleData> = JComboBox<ArticleData>()
    private val button = JButton("Show details")



    init {
        controller.registerView(this, INewsModel.DATATYPE_ARTICLE)
        NewsList.addActionListener(this)
        button.addActionListener(this)
        this.setSize(800,400)
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.layout = BorderLayout()
        this.contentPane = createPanel()
    }

    private fun createNewsComboBox(): JPanel{
        var contentPane:JPanel = JPanel()
        contentPane.layout = BorderLayout()
        contentPane.add(JLabel("News :"), BorderLayout.WEST)
        contentPane.add(NewsList, BorderLayout.CENTER)

        return contentPane
    }
    private fun createArticlePanel(): JPanel{
        var contentPane:JPanel = JPanel()
        contentPane.layout = GridLayout(3,3)
        contentPane.add(JLabel("Nom : "))
        contentPane.add(labelName)
        contentPane.add(JLabel("Article : "))
        contentPane.add(articleLabel)

        return contentPane
    }
    private fun createPanel(): JPanel{
        var mainPanel:JPanel = JPanel()
        mainPanel.layout = BorderLayout()
        mainPanel.add(createNewsComboBox(), BorderLayout.NORTH)
        mainPanel.add(createArticlePanel(), BorderLayout.CENTER)
        mainPanel.add(button, BorderLayout.SOUTH)
        return mainPanel
    }
    override fun display() {
        this.isVisible = true

    }
    override fun close() {
        this.close()
    }

    override fun propertyChange(evt: PropertyChangeEvent?) {
        if(evt?.newValue is Article){        //parce que le model a un attribut de type StationInformation
            NewsList.model = DefaultComboBoxModel<ArticleData>((evt.newValue as Article).articles.toTypedArray())
        }

        else println("unknown data or null")
    }
    override fun actionPerformed(e: ActionEvent?) {

        if(e?.source is JComboBox<*>){
            this.controller.changeSelectedArticle( NewsList.model.getElementAt(NewsList.selectedIndex).author, NewsList.model.getElementAt(NewsList.selectedIndex).title)
            labelName.text = (NewsList.getItemAt(NewsList.selectedIndex) as ArticleData).author
            articleLabel.text = (NewsList.getItemAt(NewsList.selectedIndex) as ArticleData).title
        }else if(e?.source == button){
            this.controller.changeSelectedArticle( NewsList.model.getElementAt(NewsList.selectedIndex).author, NewsList.model.getElementAt(NewsList.selectedIndex).title)
            this.controller.displayDetails()
        }
    }


}