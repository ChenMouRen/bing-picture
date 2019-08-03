package com.chen.bing.picture.bean

import lombok.Data
import java.text.DateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import javax.persistence.*

/**
 * @author: 陈海楠
 * @date: 2019/7/25
 * @description:
 */
@Table(name = "picture")
@Entity
class Picture : Comparable<Picture> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "releasedate")
    var releaseDate: String? = null

    @Column(name = "url")
    var url: String? = null

    @Column(name = "copyright")
    var copyRight: String? = null

    override fun compareTo(other: Picture): Int {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return 0 - Period.between(LocalDate.parse(releaseDate, dateFormat),
                LocalDate.parse(other.releaseDate, dateFormat)).days
    }

    override fun toString(): String {
        return "Picture(id=$id, releaseDate=$releaseDate, url=$url, copyRight=$copyRight)"
    }


}