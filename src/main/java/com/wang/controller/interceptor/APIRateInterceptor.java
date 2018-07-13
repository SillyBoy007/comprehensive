package com.wang.controller.interceptor;

import com.wang.service.RateLimiterService;
import com.wang.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 令牌桶算法拦截器，在所有请求前进行API访问限速
 */
@Controller
public class APIRateInterceptor implements HandlerInterceptor {
    @Autowired
    private RateLimiterService rateLimiterService;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {


        try {
            String username = (String) httpServletRequest.getSession().getAttribute("username");
            if (!StringUtils.isEmpty(username) && !rateLimiterService.acquire("limiter:" + username, 1, System.currentTimeMillis())) {

                httpServletRequest.getSession().setAttribute("msg","操作太过频繁");
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
