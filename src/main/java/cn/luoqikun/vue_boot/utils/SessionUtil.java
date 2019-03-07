package cn.luoqikun.vue_boot.utils;

/**
 * @Author: lqk
 * @Date: 2019/1/11 8:35
 * @Version: 1.0
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.luoqikun.vue_boot.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionUtil {

    private static final String SESSION_USER = "session_user";

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }


    //设置用户信息到session
    public static void setUserSession(User user) {
        getRequest().getSession(true).setAttribute(SESSION_USER, user);
    }

    //重Session中获取用户信息
    public static User getUserSession() {
        return (User) getRequest().getSession(true).getAttribute(SESSION_USER);
    }

    //清空session
    public static void removeSession() {
        getRequest().getSession().removeAttribute(SESSION_USER);
    }

    /**
     * 获取session
     *
     * @return
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }


    /**
     * 获取真实路径
     *
     * @return
     */
    public static String getRealRootPath() {
        return getRequest().getServletContext().getRealPath("/");
    }

    /**
     * 获取上下文path
     *
     * @return
     */
    public static String getContextPath() {
        return getRequest().getContextPath();
    }

    /**
     * 获取ip
     *
     * @return
     */
    public static String getIp() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return request.getRemoteAddr();
        }
        return null;
    }
}
