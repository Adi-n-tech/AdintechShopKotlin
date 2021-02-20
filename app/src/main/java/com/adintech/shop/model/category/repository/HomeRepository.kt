package com.adintech.shop.model.category.repository

import android.content.Context
import com.adintech.shop.model.category.pojo.Category
import com.adintech.shop.utils.APIResponseListener

class HomeRepository {

    fun doGetCategories(
            apiResponseListener: APIResponseListener,
            context: Context,
            requestID: Int
    ) {

        val categoriesList = ArrayList<Category>()

        //adding some dummy data to the list
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

        return apiResponseListener.onSuccess(categoriesList, requestID)

    }
}