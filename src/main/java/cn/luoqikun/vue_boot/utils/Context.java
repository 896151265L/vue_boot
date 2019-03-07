package cn.luoqikun.vue_boot.utils;

import java.io.Serializable;

/**
 * @Author: lqk
 * @Date: 2019/1/10 11:31
 * @Version: 1.0
 * 常量类
 */
public class Context implements Serializable {

    private static final long serialVersionUID = -2246214272493804504L;

    public static final String CODE_SUCCESS_0000 = "0000";  //成功状态码
    public static final String CODE_FAILED_9999 = "9999";  //失败状态码

    public static final String ERROR_SPACE_CHARACTER = "账号或者密码不能包含空格";
    public static final String ERROR_ACCOUNT_OR_PWD_NOT_NULL = "账号或者密码不能为空";
    public static final String ERROR_ACCOUNT_ERROE = "账户不存在,请检查后再试";
    public static final String ERROR_PWD_ERROR = "密码错误，请输入正确密码";


}
