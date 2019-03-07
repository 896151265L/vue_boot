package cn.luoqikun.vue_boot.service;

import cn.luoqikun.vue_boot.bean.Pages;
import cn.luoqikun.vue_boot.entity.User;
import com.github.pagehelper.PageInfo;

public interface IUserService extends BaseService<User> {

    PageInfo<User> pageQuery(Pages page);  //分页查询

    //登录时根据账号查询
    User selectUser(String account);

}