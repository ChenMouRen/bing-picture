package com.chen.bing.picture.utils

import com.chen.bing.picture.bean.Picture
import com.chen.bing.picture.constants.PictureConstants
import org.dom4j.Document
import org.dom4j.io.SAXReader
import java.io.InputStream

/**
 * @author: 陈海楠
 * @date: 2019/7/25
 * @description:
 */
object XMLUtils {

    fun getPictureDataFromXMl(inputStream: InputStream): List<Picture> {
        val pictures = mutableListOf<Picture>()
        val document = initDocument(inputStream)
        val rootElement = document.rootElement
        rootElement.elementIterator(PictureConstants.nodeName).forEach {
            var picture = Picture()
            picture.releaseDate = spiltDate(it.node(2).text)
            picture.url = PictureConstants.preUrl + it.node(3).text
            picture.copyRight = it.node(5).text
            pictures.add(picture)
        }
        return pictures
    }

    private fun initDocument(inputStream: InputStream): Document {
        return SAXReader().read(inputStream)
    }

    private fun spiltDate(dateText: String): String{
        val dateBuilder = StringBuilder(dateText.substring(0,4))
        dateBuilder.append("-")
        dateBuilder.append(dateText.substring(4,6))
        dateBuilder.append("-")
        dateBuilder.append(dateText.substring(6))
        return dateBuilder.toString()
    }

}