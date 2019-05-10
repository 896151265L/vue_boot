package cn.luoqikun.vue_boot.exception;

import lombok.Data;

import java.util.logging.Logger;

/**
 * @Author: lqk
 * @Date: 2019/2/18 11:04
 * @Version: 1.0
 */
@Data
public class MyException extends Exception {

    private String code;
    private String msg;


    /**
     * @param message
     * 基本异常
     */
    public MyException(String message) {
        super(message);
    }


    /**
     * @param code  错误编号
     * @param msg   信息描述
     * 基本异常
     */
    public MyException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
