package com.example.rssreader

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RssAdapter(val context: Context, private val rssItems: ArrayList<RssItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class RssViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleLabel: TextView = view.findViewById(R.id.titleLabel)
        val descriptionLabel: TextView = view.findViewById(R.id.descriptionLabel)
        val pubDate: TextView = view.findViewById(R.id.dateLabel)

        var link: String = ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        val viewHolder = RssViewHolder(view)
        view.setOnClickListener{
            val intent = Intent(context, PageActivity::class.java)
            intent.putExtra("link", viewHolder.link)
            context.startActivity(intent)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return rssItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = rssItems[position]

        with(holder as RssViewHolder) {
            titleLabel.text = item.title
            descriptionLabel.text = item.description
            pubDate.text = item.pubDate
            link = item.link
        }

    }

}
