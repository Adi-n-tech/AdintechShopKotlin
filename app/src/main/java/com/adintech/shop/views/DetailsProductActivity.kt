package com.adintech.shop.views

import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.adintech.shop.R
import com.adintech.shop.databinding.ActivityDetailsProductBinding
import com.adintech.shop.utils.AppConstant
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ImageListener
import java.util.*

class DetailsProductActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityDetailsProductBinding
    private lateinit var name: String
    private var actualPrice: Int? = null
    private var discountPrice: Int? = null
    private var deliveryCharge: Int? = null
    private lateinit var description: String
    private lateinit var discountPercentage: String
    private lateinit var icon: String
    private lateinit var img1: String
    private lateinit var img2: String

    //variable
    private val mProductImages = ArrayList<String>(3)
    private var imageListener: ImageListener? = null

    override
    fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details_product)

        val it = intent
        name = it.getStringExtra(AppConstant.INTENT_KEYS.PRODUCT_NAME).toString()
        description = it.getStringExtra(AppConstant.INTENT_KEYS.PRODUCT_DESCRIPTION).toString()
        actualPrice = it.getIntExtra(AppConstant.INTENT_KEYS.PRODUCT_ACTUAL_PRICE, 0)
        discountPrice = it.getIntExtra(AppConstant.INTENT_KEYS.PRODUCT_DISCOUNT_PRICE, 0)
        deliveryCharge = it.getIntExtra(AppConstant.INTENT_KEYS.PRODUCT_DELIVERY_CHARGE,0)
        discountPercentage =
            it.getStringExtra(AppConstant.INTENT_KEYS.PRODUCT_DISCOUNT_PERCENTAGE).toString()
        icon = it.getStringExtra(AppConstant.INTENT_KEYS.PRODUCT_ICON).toString()
        img1 = it.getStringExtra(AppConstant.INTENT_KEYS.PRODUCT_IMAGE1).toString()
        img2 = it.getStringExtra(AppConstant.INTENT_KEYS.PRODUCT_IMAGE2).toString()

        imageListener = ImageListener { position, imageView ->
            Picasso.get()
                .load(mProductImages.get(position))
                .error(R.drawable.no_image_icon)
                .placeholder(R.drawable.no_image_icon)
                .into(imageView)
        }

        mBinding.productImage.setImageListener(imageListener)
        mBinding.productImage.setPageCount(mProductImages.size)

        mBinding.productName.text=name
        mBinding.productSummary.text=description
        mBinding.productPriceOriginal.text= actualPrice.toString()
        //strike through actual price
        mBinding.productPriceOriginal.setPaintFlags(mBinding.productPriceOriginal.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
        mBinding.discountProductPrice.text=discountPrice.toString()
        mBinding.productPricePer.text=discountPercentage+" % OFF"
        mBinding.deliveryCharge.text= "Delivery Charge: "+deliveryCharge.toString()
    }
}