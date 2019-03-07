package cn.luoqikun.vue_boot.security;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lqk
 * @Date: 2019/1/7 17:45
 * @Version: 1.0
 */
//implements HandlerInterceptor
public class MyIntercpetor  {

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //       User user = SessionUtil.getUserSession();
////        if(null == user){
////            //页面拦截器
////            System.out.println("session为空");
////     response.setStatus(HttpStatus.UNAUTHORIZED.value());
////        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
}
