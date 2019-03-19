package cn.luoqikun.vue_boot.config.pay_config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lqk
 * @Date: 2019/3/6 16:57  读取配置文件参数
 * @Version: 1.0
 */
@Data
@Configuration
public class ConfigProperty {

    //电脑网页支付配置参数
    @Value("${aliPcPay.url}")
    private  String url;
    @Value("${aliPcPay.appId}")
    private String appId;
    @Value("${aliPcPay.appPrivateKey}")
    private String appPrivateKey;
    @Value("${aliPcPay.format}")
    private String format;
    @Value("${aliPcPay.charset}")
    private String charset;
    @Value("${aliPcPay.aliPayPublicKey}")
    private String aliPayPublicKey;
    @Value("${aliPcPay.signType}")
    private String signType;


}
