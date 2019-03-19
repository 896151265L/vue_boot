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


    public MyException(String msg){
        super(msg);
    };

}
