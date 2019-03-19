package cn.luoqikun.vue_boot.controller.login;

import cn.luoqikun.vue_boot.bean.ResponseBean;
import cn.luoqikun.vue_boot.controller.base.BaseController;
import cn.luoqikun.vue_boot.entity.User;
import cn.luoqikun.vue_boot.service.IUserService;
import cn.luoqikun.vue_boot.utils.Context;
import cn.luoqikun.vue_boot.utils.SessionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: lqk
 * @Date: 2019/1/10 9:11
 * @Version: 1.0
 * 登录controller
 */
@RestController
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController extends BaseController {


    @Autowired
    private IUserService userServiceImpl;

    //登录
    @PostMapping("/login")
    public ResponseBean<Object> login(@RequestBody User user) {
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
    }
    @PostMapping("/err")
    public void test(User user)throws Exception{

        try{
            Integer a = 1/0;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("0不能做除数");
        }
    }
}
