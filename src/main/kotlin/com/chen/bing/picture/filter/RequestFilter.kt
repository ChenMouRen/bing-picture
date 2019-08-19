package com.chen.bing.picture.filter

import com.chen.bing.picture.constants.PictureConstants
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest

/**
 * @author: 1806632927@qq.com
 * @date: 2019/7/24
 * @version 1.0
 * @description: 拦截器,用于拦截搜索引擎抓取导致的服务器大量404日志
 */
@Component
@WebFilter(filterName = "requestFilter",urlPatterns = ["/*"])
class RequestFilter: Filter {

    private val logger = LoggerFactory.getLogger(RequestFilter::class.java)

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val servletRequest = request as HttpServletRequest
        if (AntPathMatcher().match(PictureConstants.requestPath,servletRequest.requestURI)){
            chain!!.doFilter(request, response)
        } else {
            logger.info(servletRequest.requestURI)
        }
    }
}