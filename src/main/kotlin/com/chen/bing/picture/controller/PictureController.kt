package com.chen.bing.picture.controller

import com.chen.bing.picture.bean.Picture
import com.chen.bing.picture.constants.PictureConstants
import com.chen.bing.picture.dao.PictureRepository
import com.chen.bing.picture.utils.OkHttpUtils
import com.chen.bing.picture.utils.XMLUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
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

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<Any,Any>

    @GetMapping("/all")
    fun getPicture(): List<Picture>{
        var pictureData = redisTemplate.opsForList().rightPop("pictureData")
        if (pictureData == null) {
            pictureData = pictureRepository.findAll()
            redisTemplate.opsForList().rightPush("pictureData", pictureData)
        }
        return pictureData as List<Picture>
    }

    @GetMapping("/date")
    fun getPictureByDate(date: String): Picture{
        var picture = redisTemplate.opsForValue().get(date)
        if (picture == null) {
            println(picture == null)
            picture =  pictureRepository.findByReleaseDate(date)
            picture.releaseDate?.let { redisTemplate.opsForValue().set(it,picture) }
        }
        return picture as Picture
    }

}