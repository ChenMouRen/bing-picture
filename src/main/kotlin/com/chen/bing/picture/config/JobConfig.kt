package com.chen.bing.picture.config


import com.chen.bing.picture.constants.PictureConstants
import com.chen.bing.picture.dao.PictureRepository
import com.chen.bing.picture.utils.OkHttpUtils
import com.chen.bing.picture.utils.XMLUtils
import lombok.extern.slf4j.Slf4j
import org.omg.CORBA.Object
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.connection.RedisConnection
import org.springframework.data.redis.core.RedisCallback
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

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
        val response = OkHttpUtils.sendSynchronizeRequestToGetPictureData(PictureConstants.baseUrL, 0, 1)
        if (response.isSuccessful) {
            val pictureData = XMLUtils.getPictureDataFromXMl(response.body!!.byteStream())
            logger.info(pictureData.toString())
            pictureData.takeIf { pictureData.isNotEmpty() }.let {
                redisTemplate.execute { connection ->
                    connection.flushAll()
                    null
                }
                pictureRepository.saveAll(pictureData)
            }
        } else {
            logger.error("未能成功获取到数据信息")
        }
    }

}