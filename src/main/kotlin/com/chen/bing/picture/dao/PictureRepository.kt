package com.chen.bing.picture.dao

import com.chen.bing.picture.bean.Picture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author: 陈海楠
 * @date: 2019/7/25
 * @description:
 */
@Repository
interface PictureRepository: JpaRepository<Picture,Long> {

    fun findByReleaseDate(releaseDate: String): Picture

}