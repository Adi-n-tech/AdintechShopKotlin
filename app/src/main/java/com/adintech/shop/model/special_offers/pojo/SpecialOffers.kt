package com.adintech.shop.model.special_offers.pojo

data class SpecialOffers(
    val name: String,
    val icon: String,
    val actual_price: String,
    val discount_price: String,
    val discount_per: String,
    val description: String,
    val delivery_charge: String
)