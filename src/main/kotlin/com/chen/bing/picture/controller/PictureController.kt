package com.chen.bing.picture.controller

import com.alibaba.fastjson.JSON
import com.chen.bing.picture.bean.Picture
import com.chen.bing.picture.constants.PictureConstants
import com.chen.bing.picture.dao.PictureRepository
import com.chen.bing.picture.utils.OkHttpUtils
import com.chen.bing.picture.utils.XMLUtils
import org.hibernate.cfg.MetadataSourceType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

/**
 * @author: 陈海楠
 * @date: 2019/7/25
 * @description:
 */
@RestController
@RequestMapping("/picture",produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class PictureController {

    @Autowired
    private lateinit var pictureRepository: PictureRepository

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, String>

    @GetMapping("/all")
    fun getPicture(): String? {
        var pictureData: String? = null
        println(redisTemplate.keys("*"))
        if (redisTemplate.keys("*").contains(PictureConstants.allDataKeyName)) {
            pictureData = redisTemplate.opsForValue().get("pictureData") as String
        } else {
            pictureData = JSON.toJSONString(pictureRepository.findAll())
            redisTemplate.opsForValue().set(PictureConstants.allDataKeyName, pictureData)
            redisTemplate.expire(PictureConstants.allDataKeyName,24,TimeUnit.HOURS)
        }
        return pictureData
    }

    @GetMapping("/date")
    fun getPictureByDate(date: String): String? {
        var picture: String? = null
        if (redisTemplate.keys("*").contains(date)) {
           picture = redisTemplate.opsForValue().get(date)
        } else {
            pictureRepository.findByReleaseDate(date)?.let {
                picture = JSON.toJSONString(it)
                redisTemplate.opsForValue().set(date,picture!!)
            }
        }
        return picture
    }

}