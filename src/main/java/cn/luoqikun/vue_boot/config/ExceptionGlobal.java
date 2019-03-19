package cn.luoqikun.vue_boot.config;

import cn.luoqikun.vue_boot.bean.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionGlobal {

    @ExceptionHandler(Exception.class)
    public ResponseData exceptionHandler(Exception e) {
        log.error("service err: {}", e);
        ResponseData res = new ResponseData();
        res.setCode(-1);
        res.setMsg("未知错误");
        return res;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseData businessHandler(Exception e) {
        String message = e.getMessage();
        log.warn("service warn: {}", message);
        ResponseData res = new ResponseData();
        res.setCode(-1);
        res.setMsg(message);
        return res;
    }

}
