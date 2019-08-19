package com.chen.bing.picture.utils

import java.time.Duration.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/**
 * @author 1806632927@qq.com
 * @date 2019/8/17
 * @version 1.0
 * @description: 日期操作封装工具类
 */
object DateUtils {

    private const val dateRegex = "^\\d{4}-\\d{2}-\\d{2}"

    /**
     * 验证日期是否为 yyyy-MM-dd 格式
     * 判断日期是否为合法日期,如 2019-08-17 2019-08-32
     */
    fun isValidDate(str: String): Boolean {
        if (str.matches(Regex(dateRegex))) {
            return try {
                val date = DateTimeFormatter.ISO_DATE.parse(str)
                true
            } catch (d: DateTimeParseException) {
                false
            }
        }
        return false
    }

    /**
     * 获取当天的日期字符串
     */
    fun getTodayDateString(): String = LocalDate.now().format(DateTimeFormatter.ISO_DATE)

    /**
     * 根据当前时间来确定返回哪个链接,9点30之前当天的数据还没有获取,故返回昨天的链接
     */
    fun getDateStringByTime(): String {
        val seconds = between(LocalTime.now(), LocalTime.of(9, 30)).seconds
        // 大于0就还没有到9点半,就需要返回昨天的链接
        if (seconds > 0) {
            return LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE)
        }
        return getTodayDateString()
    }
}