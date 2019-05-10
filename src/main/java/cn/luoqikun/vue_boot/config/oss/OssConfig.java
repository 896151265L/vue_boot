package cn.luoqikun.vue_boot.config.oss;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;

/**
 * @Author: lqk
 * @Date: 2019/3/25 20:20
 * @Version: 1.0
 */
@Data
@SpringBootConfiguration
public class OssConfig {

    @Value("${OSS.endPoint}")
    private String endPoint;
    @Value("${OSS.accessKeyId}")
    private String accessKeyId;
    @Value("${OSS.accessKeySecret}")
    private String accessKeySecret;
    @Value("${OSS.bucKetName}")
    private String bucketName;

}
