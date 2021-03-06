package com.adintech.shop.model.category.repository

import android.content.Context
import com.adintech.shop.model.category.pojo.Category
import com.adintech.shop.utils.APIResponseListener
import com.practice.samplekotlinapp.utils.Utility
import org.json.JSONException
import org.json.JSONObject

class HomeRepository {

    fun doGetCategories(
        apiResponseListener: APIResponseListener,
        context: Context,
        requestID: Int
    ) {

        val categoriesList = ArrayList<Category>()
        try {
            val obj = JSONObject(Utility.loadJSONFromAsset(context, "doGetCategoriesList.json"))
            val userArray = obj.getJSONArray("categoriesList")
            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                /* personName.add(userDetail.getString("name"))
                 icon.add(userDetail.getString("icon"))*/

                categoriesList.add(
                    Category(
                        userDetail.getString("name"),
                        userDetail.getString("icon"),
                        userDetail.getString("img1"),
                        userDetail.getString("img2"),
                        userDetail.getString("actual_price"),
                        userDetail.getString("discount_price"),
                        userDetail.getString("description"),
                        userDetail.getString("rating"),
                        userDetail.getString("discount_percentage")
                    )
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return apiResponseListener.onSuccess(categoriesList, requestID)

    }
}