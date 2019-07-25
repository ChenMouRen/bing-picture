package com.chen.bing.picture.controller

import com.chen.bing.picture.bean.Picture
import com.chen.bing.picture.constants.PictureConstants
import com.chen.bing.picture.dao.PictureRepository
import com.chen.bing.picture.utils.OkHttpUtils
import com.chen.bing.picture.utils.XMLUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author: 陈海楠
 * @date: 2019/7/25
 * @description:
 */
@RestController
@RequestMapping("/picture")
class PictureController {

    @Autowired
    private lateinit var pictureRepository: PictureRepository

    @GetMapping
    fun getPicture(): List<Picture>{
        return pictureRepository.findAll()
    }

}