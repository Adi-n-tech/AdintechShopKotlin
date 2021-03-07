package com.adintech.shop.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adintech.shop.model.top_selling.repository.TopSellingRepository
import com.adintech.shop.utils.APIResponse
import com.adintech.shop.utils.APIResponseListener

class TopSellingProductViewModel : ViewModel(), APIResponseListener {

    lateinit var context: Context

    var topSellingRepository: TopSellingRepository? = null
    var apiResponseMutableLiveData: MutableLiveData<APIResponse>? = null

    fun init(context: Context) {
        this.context = context
        topSellingRepository = TopSellingRepository()
        apiResponseMutableLiveData = MutableLiveData()
    }

    override fun onSuccess(callResponse: Any?, requestID: Int?) {
        apiResponseMutableLiveData!!.postValue(APIResponse.success(callResponse!!, requestID!!))
    }

    override fun onFailure(error: Throwable?, requestID: Int?) {
        apiResponseMutableLiveData?.postValue(APIResponse.error(error!!, requestID!!))
    }

    fun doGetTopSellingList( requestID: Int?) {
        apiResponseMutableLiveData!!.value = APIResponse.loading(requestID!!)
        topSellingRepository?.doGetTopSelling(this, context, requestID)
    }
}