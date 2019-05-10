package cn.luoqikun.vue_boot.config;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);// 设置shiro的安全管理器

		//setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.html"页面 或 "/login" 映射
		shiroFilterFactoryBean.setLoginUrl("/login.html");

		// 未授权界面;
		//shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/403");

		// 拦截器。匹配原则是最上面的最优先匹配
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
		// 配置不会被拦截的链接
		filterChainDefinitionMap.put("/login/login", "anon"); // 用户登录
		filterChainDefinitionMap.put("/doLogout", "logout");// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了

        // 剩余请求需要身份认证(放在最后)
        filterChainDefinitionMap.put("/**", "authc");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);//拦截器注入
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /*
     * shiro的安全管理器
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }

    /**
     * 自定义身份认证 realm;
     */
    @Bean
    public Realm realm() {
        Realm realm = new Realm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher()); //设置密码加密方式
        return realm;
    }

    /**
     * 密码校验规则HashedCredentialsMatcher 这个类是为了对密码进行编码的 ,
     * 防止密码在数据库里明码保存 ,
     * 当然在登陆认证的时候 , 这个类也负责对用户输入的密码进行加密
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5"); //指定加密方式为MD5
        credentialsMatcher.setHashIterations(1024);     // 加密次数
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }
}

