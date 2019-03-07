package cn.luoqikun.vue_boot.exception;

import lombok.Data;

/**
 * @Author: lqk
 * @Date: 2019/2/18 11:04
 * @Version: 1.0
 */
@Data
public class MyException extends Exception {

    private String code;
    private String msg;


}
