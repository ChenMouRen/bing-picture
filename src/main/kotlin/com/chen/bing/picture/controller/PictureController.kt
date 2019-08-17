package com.chen.bing.picture.controller

import com.alibaba.fastjson.JSON
import com.chen.bing.picture.constants.PictureConstants
import com.chen.bing.picture.dao.PictureRepository
import com.chen.bing.picture.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.MediaType
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

/**
 * @author: 陈海楠
 * @date: 2019/7/25
 * @description:
 */
@RestController
@RequestMapping("/picture", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class PictureController {

    @Autowired
    private lateinit var pictureRepository: PictureRepository

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, String>

    /**
     * 获取所有的数据
     */
    @GetMapping("/all")
    fun getAllPicture(): String? = redisTemplate.opsForValue().get(PictureConstants.allDataKeyName)

    /**
     * 获取指定日期的数据
     */
    @GetMapping("/date")
    fun getPictureByDate(date: String): String? {
        if (DateUtils.isValidDate(date)) {
            return redisTemplate.opsForValue().get(date)
                    .let {
                        if (it == null) {
                            pictureRepository.findByReleaseDate(date)?.apply {
                                val jsonString = JSON.toJSONString(this)
                                redisTemplate.opsForValue().set(date, jsonString)
                                return@let jsonString
                            }
                        }
                        return@let it
                    }
        }
        return "请求的日期格式不正确"
    }

    /**
     * 自动返回当天的数据
     */
    @GetMapping
    fun getTodayPicture(): String? = redisTemplate.opsForValue().get(DateUtils.getDateStringByTime())

}