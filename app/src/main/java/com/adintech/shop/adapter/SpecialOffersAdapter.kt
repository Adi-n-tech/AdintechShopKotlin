package com.adintech.shop.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adintech.shop.R
import com.adintech.shop.model.special_offers.pojo.SpecialOffers
import com.adintech.shop.utils.AppConstant
import com.adintech.shop.views.DetailsProductActivity
import com.squareup.picasso.Picasso

class SpecialOffersAdapter(
    private var context: Context,
    private var specialOffersList: ArrayList<SpecialOffers>

) :
    RecyclerView.Adapter<SpecialOffersAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_special_offers_list, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // set the data in items
        var specialOffers: SpecialOffers = specialOffersList.get(position)
        holder.name.text = specialOffers.name
        holder.actual_price.text = "₹ " + specialOffers.actual_price
        holder.discount_price.text = " ₹ " + specialOffers.discount_price
        holder.discount_per.text = specialOffers.discount_per + "\n OFF"

        //strike through actual price
        holder.actual_price.setPaintFlags(holder.actual_price.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

        Picasso.get()
            .load(specialOffers.icon)
            .placeholder(R.drawable.no_image_icon)
            .error(R.drawable.no_image_icon)
            .into(holder.image)

        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener {
            val specialOffersList: SpecialOffers = specialOffersList.get(position)
            val intent = Intent(context, DetailsProductActivity::class.java)
            intent.putExtra(AppConstant.INTENT_KEYS.PRODUCT_NAME, specialOffersList.name)
            intent.putExtra(AppConstant.INTENT_KEYS.PRODUCT_ICON, specialOffersList.icon)
            intent.putExtra(AppConstant.INTENT_KEYS.PRODUCT_DESCRIPTION, specialOffersList.description)
            intent.putExtra(AppConstant.INTENT_KEYS.PRODUCT_DELIVERY_CHARGE, specialOffersList.delivery_charge)
            intent.putExtra(AppConstant.INTENT_KEYS.PRODUCT_DISCOUNT_PERCENTAGE, specialOffersList.discount_per)
            intent.putExtra(
                AppConstant.INTENT_KEYS.PRODUCT_ACTUAL_PRICE,
                specialOffersList.actual_price
            )
            intent.putExtra(
                AppConstant.INTENT_KEYS.PRODUCT_DISCOUNT_PRICE,
                specialOffersList.discount_price
            )
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return specialOffersList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById<View>(R.id.specialOfferName) as TextView
        var image: ImageView = itemView.findViewById<View>(R.id.specialOfferIcon) as ImageView
        var actual_price: TextView = itemView.findViewById<View>(R.id.actual_price) as TextView
        var discount_price: TextView =
            itemView.findViewById<View>(R.id.discount_price) as TextView
        var discount_per: TextView = itemView.findViewById<View>(R.id.discount_per) as TextView
    }
}