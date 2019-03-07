package cn.luoqikun.vue_boot.config;

import cn.luoqikun.vue_boot.entity.User;
import cn.luoqikun.vue_boot.exception.MyException;
import cn.luoqikun.vue_boot.service.IUserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: lqk
 * @Date: 2019/1/14 20:42
 * @Version: 1.0
 */
public class Realm extends AuthorizingRealm {

    @Autowired
    private IUserService userServiceImpl;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("进入权限验证方法。。。。。。");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();


        return authorizationInfo;
    }

    //登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        System.out.println("登录认证开始执行。。。。。。");
        String account = (String) token.getPrincipal();// 从Token中获得当前用户的账号
        User user = userServiceImpl.selectUser(account);//从数据库中根据用户名查询用户
        if (null == user) {
            return null;
        }
        ByteSource salt = ByteSource.Util.bytes(account); //把账号作为盐值
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                account,            //账号
                user.getPassWord(), //数据库密码
                salt,               //盐值
                getName()
        );
        return info;
    }
}
