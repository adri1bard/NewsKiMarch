package org.isen.News.view.impl

import org.isen.News.controller.NewsController
import org.isen.News.model.INewsModel
import org.isen.News.model.data.ArticleData
import org.isen.News.view.INewsView
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.beans.PropertyChangeEvent
import javax.swing.*

class NewsDetailsView(var controller: NewsController): JFrame("News"), INewsView {
    private val titleLabel = JLabel()
    private val articleText = JTextArea(5, 50)
    private val authorLabel = JLabel()




    init {
        controller.registerView(this, INewsModel.DATATYPE_ARTICLE)
        this.setSize(800,400)
        this.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        this.layout = BorderLayout()
        this.contentPane = createPanel()
    }
    private fun createPanel(): JPanel{
        var mainPanel:JPanel = JPanel()
        mainPanel.layout = BorderLayout()
        mainPanel.add(createArticlePanel(), BorderLayout.CENTER)
        return mainPanel
    }
//    private fun createArticlePanel(): JPanel{
//        var contentPane:JPanel = JPanel()
//        contentPane.layout = FlowLayout(FlowLayout.LEFT)
//
//        contentPane.add(JLabel("Titre : "))
//        contentPane.add(titleLabel)
//
//        articleText.lineWrap = true
//        articleText.wrapStyleWord = true
//        contentPane.add(JLabel("Article : "))
//        contentPane.add(JScrollPane(articleText))
//        contentPane.add(JLabel("Auteur : "))
//        contentPane.add(authorLabel)
//
//
//        return contentPane
//    }
private fun createArticlePanel(): JPanel {
    val contentPane = JPanel()
    contentPane.layout = BoxLayout(contentPane, BoxLayout.Y_AXIS)

    contentPane.add(createTitleComponent("Titre :", titleLabel))
    contentPane.add(createTitleComponent("Article :", JScrollPane(articleText)))
    contentPane.add(createTitleComponent("Auteur :", authorLabel))

    return contentPane
}

    private fun createTitleComponent(title: String, component: JComponent): JPanel {
        val panel = JPanel()
        panel.layout = FlowLayout(FlowLayout.LEFT)
        panel.add(JLabel("$title "))
        panel.add(component)
        return panel
    }


    override fun display() {
        this.isVisible = true
    }

    override fun close() {
        this.close()
    }

    override fun propertyChange(evt: PropertyChangeEvent?) {
        println("Received event")
        if(evt?.newValue is ArticleData) {
            // logger.info("Received article details")
            (evt.newValue as ArticleData).let {
                titleLabel.text = it.title.toString()
                articleText.text = it.description.toString()
                authorLabel.text = it.author.toString()
    }

    }
    }

    override fun actionPerformed(e: ActionEvent?) {
        TODO("Not yet implemented")
    }

}