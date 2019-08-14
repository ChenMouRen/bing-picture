package com.chen.bing.picture.filter

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
 * @description:
 */
@Component
@WebFilter(filterName = "requestFilter",urlPatterns = ["/*"])
class RequestFilter: Filter {

    private val path = "/picture/**"

    private val logger = LoggerFactory.getLogger(RequestFilter::class.java)

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val servletRequest = request as HttpServletRequest
        if (AntPathMatcher().match("/picture/**",servletRequest.requestURI)){
            chain!!.doFilter(request, response)
        } else {
            logger.info(servletRequest.requestURI)
        }
    }
}