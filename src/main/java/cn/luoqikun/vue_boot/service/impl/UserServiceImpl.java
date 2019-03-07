package cn.luoqikun.vue_boot.service.impl;

import cn.luoqikun.vue_boot.bean.Pages;
import cn.luoqikun.vue_boot.entity.User;
import cn.luoqikun.vue_boot.mapper.UserMapper;
import cn.luoqikun.vue_boot.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void update(User user) {
        userMapper.updateById(user);
    }

    @Override
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    //分页查询
    @Override
    public PageInfo<User> pageQuery(Pages page) {
        return PageHelper.startPage(page).doSelectPageInfo(() -> userMapper.selectList(null));
    }

    //登录时根据登录账号查询
    @Override
    public User selectUser(String account) {
        //根据登录账号查询用户对象
        LambdaQueryWrapper<User> eq = new QueryWrapper<User>().lambda().eq(User::getAccount, account);
        return userMapper.selectOne(eq);
    }
}
