package com.example.rssreader

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream

class RssParser {
    fun parse(inputStream: InputStream): ArrayList<RssItem> {
        val rssItems = ArrayList<RssItem>()
        var text: String? = null

        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val parser = factory.newPullParser()
        parser.setInput(inputStream, null)
        var eventType = parser.eventType
        var foundItem = false

        var title = ""
        var link = ""
        var description = ""
        var pubDate = ""

        while (eventType != XmlPullParser.END_DOCUMENT) {
            val tagName = parser.name
            when (eventType) {
                XmlPullParser.START_TAG -> if (tagName.equals("item", ignoreCase = true)) {
                    foundItem = true
                    title = ""
                    link = ""
                    description = ""
                    pubDate = ""
                }
                XmlPullParser.TEXT -> text = parser.text
                XmlPullParser.END_TAG -> if (tagName.equals("item", ignoreCase = true)) {
                    if (title == "" || link == "" || pubDate == "")
                        throw java.lang.Exception("Syntax error")
                    rssItems.add(RssItem(title, link, description, pubDate))
                    foundItem = false
                } else if (foundItem && tagName.equals("title", ignoreCase = true)) {
                    title = text.toString()
                } else if (foundItem && tagName.equals("link", ignoreCase = true)) {
                    link = text.toString()
                } else if (foundItem && tagName.equals("description", ignoreCase = true)) {
                    description = text.toString()
                } else if (foundItem && tagName.equals("pubDate", ignoreCase = true)) {
                    pubDate = text.toString()
                }
            }
            eventType = parser.next()
        }

        return rssItems
    }
}