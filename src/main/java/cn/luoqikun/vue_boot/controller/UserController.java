package cn.luoqikun.vue_boot.controller;

import cn.luoqikun.vue_boot.bean.Pages;
import cn.luoqikun.vue_boot.bean.ResponseBean;
import cn.luoqikun.vue_boot.controller.base.BaseController;
import cn.luoqikun.vue_boot.entity.User;
import cn.luoqikun.vue_boot.service.IUserService;
import cn.luoqikun.vue_boot.utils.Context;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: lqk
 * @Date: ${Date} 10:45
 * @Version: 1.0
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userServiceImpl;

    //新增用户
    @RequestMapping("saveUser")
    public ResponseBean<Void> saveUser(User user) {
        if (user.getAccount().contains(" ") || user.getPassWord().contains(" ")) {
            return failedMsg(Context.ERROR_SPACE_CHARACTER);
        }
        ByteSource salt = ByteSource.Util.bytes(user.getAccount());//把账号作为盐值
        //加密方式，被加密的密码，盐值，加密次数
        String newPs = new SimpleHash("MD5", user.getPassWord(), salt, 1024).toHex();
        user.setPassWord(newPs);
        userServiceImpl.save(user);
        return success();

    }

    //查询全部用户(不分页)
    @RequestMapping("getAll")
    public ResponseBean<List<User>> getAll() {
        return successData(userServiceImpl.getAll());
    }

    //分页查询全部
    @RequestMapping("pageQuery")
    public PageInfo<User> pageQuery(Pages page) {
        return userServiceImpl.pageQuery(page);
    }


}
