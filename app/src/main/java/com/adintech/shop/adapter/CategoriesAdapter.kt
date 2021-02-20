package com.adintech.shop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adintech.shop.DataObject
import com.adintech.shop.R

class CategoriesAdapter(val categoriesList: ArrayList<DataObject>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categories_list, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(categoriesList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return categoriesList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(categories_list: DataObject) {
            val categoriesName = itemView.findViewById(R.id.categoriesName) as TextView
            val categoriesIcon = itemView.findViewById(R.id.categoriesIcon) as ImageView
            categoriesName.text = categories_list.name



        }
    }
}