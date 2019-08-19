package com.chen.bing.picture.dao

import com.chen.bing.picture.bean.Picture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author: 1806632927@qq.com
 * @date: 2019/7/25
 * @version 1.0
 * @description: 数据库持久层操作
 */
@Repository
interface PictureRepository: JpaRepository<Picture,Long> {

    /**
     * 根据日期来查找图片信息
     */
    fun findByReleaseDate(releaseDate: String): Picture?

}