package com.ppa.bitcoinapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ppa.bitcoinapi.R
import com.ppa.bitcoinapi.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_article_list.view.*

class BitcoinListAdapter (var articleList : List<Article> = ArrayList())
    : RecyclerView.Adapter<BitcoinListAdapter.BitcoinViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitcoinViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_article_list,parent,false)
        return BitcoinViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: BitcoinViewHolder, position: Int) {
        holder.bindArticle(articleList[position])
    }

    fun updateList(article: List<Article>){
        this.articleList = article
        notifyDataSetChanged()
    }

    inner class BitcoinViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        private var view: View = itemView
        private lateinit var article : Article
        fun bindArticle(article : Article){
            this.article = article

            Picasso.get()
                .load(article.urlToImage)
                .placeholder(R.drawable.loading)
                .into(view.img_Article)

            view.txt_Title.text = article.title
            view.txt_Author.text = article.author
            view.txt_Description.text = article.description
        }
    }



}