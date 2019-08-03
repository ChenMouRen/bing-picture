package com.chen.bing.picture

import com.chen.bing.picture.constants.PictureConstants
import com.chen.bing.picture.dao.PictureRepository
import com.chen.bing.picture.utils.OkHttpUtils
import com.chen.bing.picture.utils.XMLUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class PictureApplicationTests {

	private val logger = LoggerFactory.getLogger("PictureApplicationTests")

	@Autowired
	private lateinit var pictureRepository: PictureRepository

	@Autowired
	private lateinit var redisTemplate: RedisTemplate<Any, Any>


	@Test
	fun contextLoads() {
		val response = OkHttpUtils.sendSynchronizeRequestToGetPictureData(PictureConstants.baseUrL, 0, 2)
		if (response.isSuccessful) {
			val pictureData = XMLUtils.getPictureDataFromXMl(response.body!!.byteStream())
			pictureData.takeIf { pictureData.isNotEmpty() }.let {
				Collections.sort(pictureData)
				logger.info(pictureData.toString())
				pictureRepository.saveAll(pictureData)
			}
		} else {
			logger.error("未能成功获取到数据信息")
		}
	}

	@Test
	fun redisTest(){
//		redisTemplate.opsForList().set("pictureData",)
		redisTemplate.opsForList().rightPush("data", "Hello")
		println(redisTemplate.opsForList().rightPop("data"))
	}

}
