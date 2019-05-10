package cn.luoqikun.vue_boot.controller.login;

import cn.luoqikun.vue_boot.bean.ResponseBean;
import cn.luoqikun.vue_boot.controller.base.BaseController;
import cn.luoqikun.vue_boot.entity.User;
import cn.luoqikun.vue_boot.service.IUserService;
import cn.luoqikun.vue_boot.utils.Context;
import cn.luoqikun.vue_boot.utils.SessionUtil;
import lombok.SneakyThrows;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @Author: lqk
 * @Date: 2019/1/10 9:11
 * @Version: 1.0
 * 登录controller
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

    private Lock lock = new ReentrantLock();   //定义锁对象 解决并发问题

    @Autowired
    private IUserService userServiceImpl;

    //登录
    @SneakyThrows
    @PostMapping("/login")
    public ResponseBean<Object> login(@RequestBody User user) {
        lock.lock();//上锁
        try {
            if (StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassWord())) {
                return failedMsg(Context.ERROR_ACCOUNT_OR_PWD_NOT_NULL);
            }
            UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassWord());
            Subject subject = SecurityUtils.getSubject();
            try {
                subject.login(token);
            } catch (UnknownAccountException e) {
                return failedMsg(Context.ERROR_ACCOUNT_ERROE);
            } catch (IncorrectCredentialsException e) {
                return failedMsg(Context.ERROR_PWD_ERROR);
            }
            //把用户信息放到session
            SessionUtil.setUserSession(userServiceImpl.selectUser(user.getAccount()));
            return success();
        }finally {
            lock.unlock(); //释放锁
        }
    }
}
