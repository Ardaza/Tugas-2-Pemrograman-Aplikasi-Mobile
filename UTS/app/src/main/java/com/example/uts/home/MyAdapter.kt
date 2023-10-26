package com.example.uts.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.uts.R
import org.w3c.dom.Text

class MyAdapter(private var list: ArrayList<User>) : RecyclerView.Adapter<MyAdapter.MyviewHolder>() {

    var onItemClick : ((User) -> Unit)? = null

    fun setFilteredList(list: ArrayList<User>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyviewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        var currenItem = list[position]
        holder.titleImage.setImageResource(currenItem.titleImage)
        holder.tvEmail.text = currenItem.email

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(currenItem)
        }
    }

    class MyviewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titleImage : AppCompatImageView = itemView.findViewById(R.id.imageView)
        val tvEmail : TextView = itemView.findViewById(R.id.text_Email)
    }

}