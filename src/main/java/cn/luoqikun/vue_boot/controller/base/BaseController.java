package cn.luoqikun.vue_boot.controller.base;

import cn.luoqikun.vue_boot.bean.ResponseBean;
import cn.luoqikun.vue_boot.utils.Context;

/**
 * @Author: lqk
 * @Date: 2019/1/10 11:15
 * @Version: 1.0
 */
public class BaseController {

    /**
     * 成功下的返回状态
     */
    protected <T> ResponseBean<T> success() {
        return new ResponseBean<T>(Context.CODE_SUCCESS_0000);
    }

    /**
     * @param data
     * @param <T>
     * @return 成功返回状态 和数据
     */
    protected <T> ResponseBean<T> successData(T data) {
        return new ResponseBean<T>(Context.CODE_SUCCESS_0000, data);
    }

    /**
     * @param msg
     * @param <T>
     * @return 失败返回失败信息和状态码
     */
    protected <T> ResponseBean<T> failedMsg(String msg) {
        return new ResponseBean<T>(Context.CODE_FAILED_9999, msg);
    }
}
