package com.adintech.shop.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adintech.shop.model.special_offers.repository.SpecialOffersRepository
import com.adintech.shop.utils.APIResponse
import com.adintech.shop.utils.APIResponseListener

class SpecialOfferViewModel : ViewModel(), APIResponseListener {

    lateinit var context: Context

    var specialOffersRepository: SpecialOffersRepository? = null
    var apiResponseMutableLiveData: MutableLiveData<APIResponse>? = null

    fun init(context: Context) {
        this.context = context
        specialOffersRepository = SpecialOffersRepository()
        apiResponseMutableLiveData = MutableLiveData()
    }

    override fun onSuccess(callResponse: Any?, requestID: Int?) {
        apiResponseMutableLiveData!!.postValue(APIResponse.success(callResponse!!, requestID!!))
    }

    override fun onFailure(error: Throwable?, requestID: Int?) {
        apiResponseMutableLiveData?.postValue(APIResponse.error(error!!, requestID!!))
    }

    fun doGetSpecialOffersList( requestID: Int?) {
        apiResponseMutableLiveData!!.value = APIResponse.loading(requestID!!)
        specialOffersRepository?.doGetSpecialOffers(this, context, requestID)
    }
}