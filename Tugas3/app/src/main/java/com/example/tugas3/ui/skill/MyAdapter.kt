package com.example.tugas3.ui.skill

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas3.R
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(private var skillist : ArrayList<Sekil>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var onItemClick : ((Sekil) -> Unit)? = null

    fun setFilteredList(list: ArrayList<Sekil>){
        this.skillist = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curitem = skillist[position]
        holder.titleImage.setImageResource(curitem.titleImage)
        holder.tvHeading.text = curitem.heading

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(curitem)
        }
    }

    override fun getItemCount(): Int {
        return skillist.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleImage : ShapeableImageView = itemView.findViewById(R.id.title_image)
        val tvHeading : TextView = itemView.findViewById(R.id.tvHeading)
    }

}