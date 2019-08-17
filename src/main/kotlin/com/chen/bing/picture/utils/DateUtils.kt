package com.chen.bing.picture.utils

import java.time.Duration.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/**
 * @author Chen
 * @date 2019/8/17
 * @version 1.0
 * <p>
 *
 * </p>
 */
object DateUtils {

    private val dateRegex = "^\\d{4}-\\d{2}-\\d{2}"

    fun isValidDate(str: String): Boolean {
        // 先判断日期是否符合格式
        if (str.matches(Regex(dateRegex))) {
            // 判断日期是否合法
            val dateFormat = DateTimeFormatter.ISO_DATE
            try {
                val date = dateFormat.parse(str)
                return true
            } catch (d: DateTimeParseException) {
                return false
            }
        }
        return false
    }

    fun getTodayDateString(): String = LocalDate.now().format(DateTimeFormatter.ISO_DATE)

    /**
     * 根据当前时间来确定返回哪个链接
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