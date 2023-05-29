package com.example.rssreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    companion object {
        private const val rssLink =
            "https://news.un.org/feed/subscribe/ru/news/region/europe/feed/rss.xml"
        val rssItems = loadItems(URL(rssLink))!!
        private fun loadItems(source: URL): ArrayList<RssItem>? {
            var items: ArrayList<RssItem>? = null
            val parser = RssParser()
            val connection = source.openConnection() as HttpURLConnection
            val thread = Thread {
                val inputStream = connection.inputStream
                items = parser.parse(inputStream)
            }
            thread.start()
            thread.join()
            return items
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<RecyclerView>(R.id.listView)

        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = RssAdapter(this, rssItems)
    }

}