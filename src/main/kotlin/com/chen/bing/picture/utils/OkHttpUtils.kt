package com.chen.bing.picture.utils

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * @author: 1806632927@qq.com
 * @date: 2019/7/25
 * @version 1.0
 * @description: 使用OKHttp3发送网络请求
 */
object OkHttpUtils {

    /**
     * 发送同步GET请求
     */
    private fun sendSynchronizeGetRequest(url: String): Response{
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()
        return okHttpClient.newCall(request).execute()
    }

    /**
     * 发送异步GET请求
     */
    private fun sendAsynchronousGetRequest(url: String, callBack: Callback) {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()
        okHttpClient.newCall(request).enqueue(callBack)
    }

    /**
     * 发送同步请求获取图片信息
     */
    fun sendSynchronizeRequestToGetPictureData(url: String,idx: Int,n: Int): Response {
        return sendSynchronizeGetRequest("${url}idx=${idx}&n=${n}")
    }

    /**
     * 发送异步请求获取图片信息
     */
    fun sendAsynchronousRequestToGetPictureData(url: String,idx: Int,n: Int,callBack: Callback){
        sendAsynchronousGetRequest("${url}idx=${idx}&n=${n}",callBack)
    }

}