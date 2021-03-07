package com.adintech.shop.model.top_selling.pojo

data class TopSelling(
    val name: String,
    val icon: String,
    val actual_price: String,
    val discount_price: String,
    val save_price: String,
    val discount_per: String,
    val delivery_charge: String,
    val description: String
)