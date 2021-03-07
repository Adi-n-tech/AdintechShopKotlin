package com.adintech.shop.model.special_offers.repository

import android.content.Context
import com.adintech.shop.model.special_offers.pojo.SpecialOffers
import com.adintech.shop.utils.APIResponseListener
import com.practice.samplekotlinapp.utils.Utility
import org.json.JSONException
import org.json.JSONObject

class SpecialOffersRepository {

    fun doGetSpecialOffers(
        apiResponseListener: APIResponseListener,
        context: Context,
        requestID: Int
    ) {

        val specialOffersList = ArrayList<SpecialOffers>()

        try {
            val obj = JSONObject(Utility.loadJSONFromAsset(context, "doGetSpecialOffersList.json"))
            val userArray = obj.getJSONArray("SpecialOffersList")
            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                specialOffersList.add(
                    SpecialOffers(
                        userDetail.getString("name"),
                        userDetail.getString("icon"),
                        userDetail.getString("actual_price"),
                        userDetail.getString("discount_percentage"),
                        userDetail.getString("discount_price"),
                        userDetail.getString("delivery_charge"),
                        userDetail.getString("description")
                    )
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return apiResponseListener.onSuccess(specialOffersList, requestID)
    }
}