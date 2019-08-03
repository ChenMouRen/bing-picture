package com.chen.bing.picture.utils

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * @author: 陈海楠
 * @date: 2019/7/25
 * @description:
 */
object OkHttpUtils {

    fun sendSynchronizeGetRequest(url: String): Response{
        var okHttpClient = OkHttpClient()
        var request = Request.Builder().url(url).build()
        return okHttpClient.newCall(request).execute()
    }

    fun sendAsynchronousGetRequest(url: String,callBack: Callback) {
        var okHttpClient = OkHttpClient()
        var request = Request.Builder().url(url).build()
        okHttpClient.newCall(request).enqueue(callBack)
    }

    fun sendSynchronizeRequestToGetPictureData(url: String,idx: Int,n: Int): Response {
        return sendSynchronizeGetRequest("${url}idx=${idx}&n=${n}")
    }

    fun sendAsynchronousRequestToGetPictureData(url: String,idx: Int,n: Int,callBack: Callback){
        sendAsynchronousGetRequest("${url}idx=${idx}&n=${n}",callBack)
    }

}