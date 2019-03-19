package cn.luoqikun.vue_boot.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ResponseData {

    private int code;
    private String msg;
    private Object data;

    public ResponseData(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.msg = httpStatus.getReasonPhrase();
    }

}
