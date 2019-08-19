package com.chen.bing.picture.bean

import java.io.Serializable
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import javax.persistence.*

/**
 * @author: 1806632927@qq.com
 * @date: 2019/7/25
 * @version 1.0
 * @description: 图片实体类
 */
@Table(name = "picture")
@Entity
class Picture : Comparable<Picture>, Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    /**
     * 图片日期
     */
    @Column(name = "releasedate")
    var releaseDate: String? = null

    /**
     * 链接
     */
    @Column(name = "url")
    var url: String? = null

    /**
     * 描述
     */
    @Column(name = "copyright")
    var copyRight: String? = null

    /**
     * 根据两个对象的日期之差做升序排序
     */
    override fun compareTo(other: Picture): Int {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return 0 - Period.between(LocalDate.parse(releaseDate, dateFormat),
                LocalDate.parse(other.releaseDate, dateFormat)).days
    }

    override fun toString(): String {
        return "Picture(id=$id, releaseDate=$releaseDate, url=$url, copyRight=$copyRight)"
    }


}