package com.adintech.shop.model.top_selling.repository

import android.content.Context

import com.adintech.shop.model.top_selling.pojo.TopSelling
import com.adintech.shop.utils.APIResponseListener
import com.practice.samplekotlinapp.utils.Utility
import org.json.JSONException
import org.json.JSONObject

class TopSellingRepository {

    fun doGetTopSelling(
        apiResponseListener: APIResponseListener,
        context: Context,
        requestID: Int
    ) {

        val topSellingList = ArrayList<TopSelling>()

        try {
            val obj = JSONObject(Utility.loadJSONFromAsset(context, "doGetTopSellingList.json"))
            val userArray = obj.getJSONArray("ProductList")
            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                /* personName.add(userDetail.getString("name"))
                 icon.add(userDetail.getString("icon"))*/

                topSellingList.add(
                    TopSelling(
                        userDetail.getString("name"),
                        userDetail.getString("icon"),
                        userDetail.getString("actual_price"),
                        userDetail.getString("discount_price"),
                        userDetail.getString("save_price"),
                        userDetail.getString("discount_percentage"),
                        userDetail.getString("delivery_charge"),
                        userDetail.getString("description")
                    )
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return apiResponseListener.onSuccess(topSellingList, requestID)

    }
}