package cn.luoqikun.vue_boot.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lqk
 * @Date: 2019/1/10 11:21
 * @Version: 1.0
 */
@Data
public final class ResponseBean<T> implements Serializable {

    private static final long serialVersionUID = -4847040774837645256L;
    /**
     * http 状态码
     * 成功：0000  失败：9999
     */
    private String code;
    /**
     * 返回的消息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private T data;

    /**
     * @param code 成功下的返回状态
     */
    public ResponseBean(String code) {
        this.code = code;
    }

    /**
     * @param code
     * @param data 成功状态下返回状态和数据
     */
    public ResponseBean(String code, T data) {
        this.code = code;
        this.data = data;
    }

    /**
     * @param code 状态码
     * @param msg  信息
     *             失败状态下返回信息
     */
    public ResponseBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
