package cn.luoqikun.vue_boot.controller.alipay;

import cn.luoqikun.vue_boot.config.pay_config.ConfigProperty;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lqk
 * @Date: 2019/3/7 11:32
 * @Version: 1.0        退款接口
 */
@RestController
@RequestMapping("aliRefund")
public class RefundController {

    @Autowired
    private ConfigProperty configProperty;

    @SneakyThrows
    @RequestMapping("refund")
    public void refund(){

        String url = configProperty.getUrl();
        String appId = configProperty.getAppId();
        String appPrivateKey = configProperty.getAppPrivateKey();
        String format = configProperty.getFormat();
        String charset = configProperty.getCharset();
        String aliPayPublicKey = configProperty.getAliPayPublicKey();
        String signType = configProperty.getSignType();

        AlipayClient alipayClient = new DefaultAlipayClient(url,appId,appPrivateKey,format,charset,aliPayPublicKey,signType);
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

        //这里写退款请求参数
        request.setBizContent("{" +
                "    \"out_trade_no\":\"20882021764312135\"," +
                "    \"refund_amount\":0.02," +
                "    \"refund_reason\":\"正常退款\"," +
                "    \"out_request_no\":\"HZ01RF001\"," +
                "    \"operator_id\":\"OP001\"," +
                "    \"store_id\":\"NJ_S_001\"," +
                "    \"terminal_id\":\"NJ_T_001\"" +
                "  }");

        AlipayTradeRefundResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            //TODO...  退款成功从后做自己的逻辑
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
