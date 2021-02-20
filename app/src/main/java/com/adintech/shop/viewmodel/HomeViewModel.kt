package com.adintech.shop.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adintech.shop.model.category.repository.HomeRepository
import com.adintech.shop.utils.APIResponse
import com.adintech.shop.utils.APIResponseListener

class HomeViewModel : ViewModel(), APIResponseListener {

    lateinit var context: Context

    var homeRepository: HomeRepository? = null
    var apiResponseMutableLiveData: MutableLiveData<APIResponse>? = null

    fun init(context: Context) {
        this.context = context
        homeRepository = HomeRepository()
        apiResponseMutableLiveData = MutableLiveData()
    }

    override fun onSuccess(callResponse: Any?, requestID: Int?) {
        apiResponseMutableLiveData!!.postValue(APIResponse.success(callResponse!!, requestID!!))
    }

    override fun onFailure(error: Throwable?, requestID: Int?) {
        apiResponseMutableLiveData?.postValue(APIResponse.error(error!!, requestID!!))
    }

    fun doGetCategoryList( requestID: Int?) {
        apiResponseMutableLiveData!!.value = APIResponse.loading(requestID!!)
        homeRepository?.doGetCategories(this, context, requestID)
    }
}