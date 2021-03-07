package com.adintech.shop.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adintech.shop.R
import com.adintech.shop.model.top_selling.pojo.TopSelling
import com.squareup.picasso.Picasso

class TopSellingAdapter(
    private var context: Context,
    private var mTopSellingList: ArrayList<TopSelling>
) :
    RecyclerView.Adapter<TopSellingAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_top_selling_list, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // set the data in items
        var topSelling: TopSelling = mTopSellingList.get(position)
        holder.name.text = topSelling.name
        holder.actual_price.text = "₹ " + topSelling.actual_price
        holder.discount_price.text = " ₹ " + topSelling.discount_price
        holder.discount_percentage.text = topSelling.discount_per + " OFF"
        holder.save_price.text = "Save ₹ "+topSelling.save_price

        //strike through actual price
        holder.actual_price.setPaintFlags(holder.actual_price.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

        Picasso.get()
            .load(topSelling.icon)
            .placeholder(R.drawable.no_image_icon)
            .error(R.drawable.no_image_icon)
            .into(holder.image)

        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener { // display a toast with person name on item click

        }
    }

    override fun getItemCount(): Int {
        return mTopSellingList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById<View>(R.id.title) as TextView
        var actual_price: TextView = itemView.findViewById<View>(R.id.actual_price) as TextView
        var discount_price: TextView = itemView.findViewById<View>(R.id.discount_price) as TextView
        var discount_percentage: TextView = itemView.findViewById<View>(R.id.discount_per) as TextView
        var save_price: TextView = itemView.findViewById<View>(R.id.save_rupees) as TextView
        var image: ImageView = itemView.findViewById<View>(R.id.topSellingIcon) as ImageView
    }
}