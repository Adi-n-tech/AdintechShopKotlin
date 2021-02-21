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

        /*  //adding some dummy data to the list
          categoriesList.add(
                  Category(
                          "Belal Khan",
                          "https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png"
                  )
          )
          categoriesList.add(
                  Category(
                          "Ramiz Khan",
                          "https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png"
                  )
          )
          categoriesList.add(
                  Category(
                          "Faiz Khan",
                          "https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png"
                  )
          )
          categoriesList.add(
                  Category(
                          "Yashar Khan",
                          "https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png"
                  )
          )
  */

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
                        userDetail.getString("icon")
                    )
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return apiResponseListener.onSuccess(categoriesList, requestID)

    }
}