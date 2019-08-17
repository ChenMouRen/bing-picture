package com.chen.bing.picture.config


import com.alibaba.fastjson.JSON.*
import com.chen.bing.picture.constants.PictureConstants
import com.chen.bing.picture.dao.PictureRepository
import com.chen.bing.picture.utils.DateUtils
import com.chen.bing.picture.utils.OkHttpUtils
import com.chen.bing.picture.utils.XMLUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

import java.util.concurrent.CompletableFuture


/**
 * @author: 陈海楠
 * @date: 2019/7/25
 * @description:
 */
@Component
@EnableScheduling
class JobConfig {

    private val logger = LoggerFactory.getLogger("JobConfig")

    @Autowired
    private lateinit var pictureRepository: PictureRepository

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<Any,Any>

    /**
     * 每天上午9点半获取数据
     */
    @Scheduled(cron = "0 30 9 * * *")
    fun getData(){
        CompletableFuture.supplyAsync{
            OkHttpUtils.sendSynchronizeRequestToGetPictureData(PictureConstants.baseUrL, 0, 1)
        }.takeIf {
            it.get().isSuccessful
        }?.thenApply {
            XMLUtils.getPictureDataFromXMl(it?.body!!.byteStream())
        }?.thenAccept{
            it.takeIf { it.isNotEmpty() }?.let {
                pictureRepository.saveAll(it)
                updateRedis()
                addDataToRedis(toJSONString(it[0]))
            }
        }
    }

    /**
     * 更新redis中的数据
     */
    private fun updateRedis(){
        val json = toJSON(pictureRepository.findAll())
        redisTemplate.opsForValue().set(PictureConstants.allDataKeyName,json)
    }

    /**
     * 将今天的数据添加到redis
     */
    private fun addDataToRedis(data: String){
        redisTemplate.opsForValue().set(DateUtils.getTodayDateString(),data)
    }

}