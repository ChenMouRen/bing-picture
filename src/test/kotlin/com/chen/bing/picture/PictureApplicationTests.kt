package com.chen.bing.picture

import com.chen.bing.picture.bean.Picture
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

	}


}
