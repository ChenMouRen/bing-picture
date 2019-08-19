package com.chen.bing.picture.utils

import com.chen.bing.picture.bean.Picture
import com.chen.bing.picture.constants.PictureConstants
import org.dom4j.Document
import org.dom4j.io.SAXReader
import java.io.InputStream
import java.util.concurrent.CompletableFuture

/**
 * @author: 1806632927@qq.com
 * @date: 2019/7/25
 * @version 2.0
 * @description: 从获取的XML中解析出实体类数据
 * @since 2.0 使用异步操作替换同步操作,当XML数量比较大时可以有效提升效率
 */
object XMLUtils {

    /**
     * 从获取的XML中解析出实体类数据
     */
    fun getPictureDataFromXMl(inputStream: InputStream): List<Picture> {
        return CompletableFuture.supplyAsync { initDocument(inputStream) }
                .thenApply { it.rootElement }
                .thenApply { it.elementIterator(PictureConstants.nodeName) }
                .thenApply {
                    val pictures = mutableListOf<Picture>()
                    it.forEach {
                        var picture = Picture()
                        picture.releaseDate = spiltDate(it.node(2).text)
                        picture.url = PictureConstants.preUrl + it.node(3).text
                        picture.copyRight = it.node(5).text
                        pictures.add(picture)
                    }
                    return@thenApply pictures
                }
                .get()
    }

    private fun initDocument(inputStream: InputStream): Document {
        return SAXReader().read(inputStream)
    }

    private fun spiltDate(dateText: String): String {
        val dateBuilder = StringBuilder(dateText.substring(0, 4))
        dateBuilder.append("-")
        dateBuilder.append(dateText.substring(4, 6))
        dateBuilder.append("-")
        dateBuilder.append(dateText.substring(6))
        return dateBuilder.toString()
    }

}

