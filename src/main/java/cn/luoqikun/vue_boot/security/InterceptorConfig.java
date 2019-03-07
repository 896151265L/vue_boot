package cn.luoqikun.vue_boot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lqk
 * @Date: 2019/1/7 17:46
 * @Version: 1.0
 * 拦截器
 */

//implements WebMvcConfigurer
@Configuration
public class InterceptorConfig {


//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        System.out.println("进入拦截器...");
////        InterceptorRegistration registration = registry.addInterceptor(new MyIntercpetor());
////
////        registration.addPathPatterns("/**");  //拦截所有
////
////        ArrayList<String> excludList = new ArrayList<>();
////        excludList.add("/login.html");      //登录页面
////        excludList.add("/login/login");     //登录url
////        registration.excludePathPatterns(excludList);
//
//    }


}
