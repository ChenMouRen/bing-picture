package com.chen.bing.picture.constants

/**
 * @author: 1806632927@qq.com
 * @date: 2019/7/25
 * @version 3.0
 * @description: 系统中的一些常量
 */
object PictureConstants {

    /**
     * 获取图片信息的请求路径
     */
    const val baseUrL = "https://cn.bing.com/HPImageArchive.aspx?"

    /**
     * 图片路径前缀
     */
    const val preUrl = "https://cn.bing.com"

    /**
     * XML节点名称
     */
    const val nodeName = "image"

    /**
     * redis中存储所有图片信息的key名称
     */
    const val allDataKeyName = "pictureData"

    /**
     * 允许访问的请求路径,用于拦截搜索引擎抓取导致的一大堆404
     */
    const val requestPath = "/picture/**"

}