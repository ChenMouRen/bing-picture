package com.chen.bing.picture.filter

import org.springframework.stereotype.Component
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

    private val path = "/bing/picture"

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val servletRequest = request as HttpServletRequest
        if (path == servletRequest?.requestURI){
            chain!!.doFilter(request, response)
        }
    }
}