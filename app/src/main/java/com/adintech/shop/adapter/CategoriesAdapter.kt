package com.adintech.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adintech.shop.R
import com.adintech.shop.model.category.pojo.Category
import com.squareup.picasso.Picasso

class CategoriesAdapter(
    private var context: Context,
    private var categoryList: ArrayList<Category>
) :
    RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categories_list, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // set the data in items
        var person: Category = categoryList.get(position)
        holder.name.text = person.name

        Picasso.get()
            .load(person.icon)
            .placeholder(R.drawable.no_image_icon)
            .error(R.drawable.no_image_icon)
            .into(holder.image)

        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener { // display a toast with person name on item click

        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById<View>(R.id.categoriesName) as TextView
        var image: ImageView = itemView.findViewById<View>(R.id.categoriesIcon) as ImageView
    }
}