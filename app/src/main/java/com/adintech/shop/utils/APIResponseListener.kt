package com.adintech.shop.utils

interface APIResponseListener {
    fun onSuccess(callResponse: Any?, requestID: Int?)

    fun onFailure(error: Throwable?, requestID: Int?)
}